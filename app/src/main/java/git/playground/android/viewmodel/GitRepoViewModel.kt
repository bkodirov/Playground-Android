package git.playground.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import git.playground.android.datalayer.api.GitHubRestApi
import git.playground.android.di.DepGraph
import git.playground.android.domain.SchedulerProvider
import git.playground.android.ui.GitRepoResponseAdapter
import git.playground.android.ui.Loading
import git.playground.android.ui.RepositoryUiState
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class GitRepoViewModel : ViewModel() {

    @Inject lateinit var gitHubRestApi: GitHubRestApi
    @Inject lateinit var schedulerProvider: SchedulerProvider
    @Inject lateinit var repoListLiveData: MutableLiveData<RepositoryUiState>

    @Volatile
    private var disposable: Disposable? = null

    init {
        DepGraph.inject(this)
    }

    fun getRepositories(): LiveData<RepositoryUiState> {
        return repoListLiveData
    }

    fun searchRepository(repositoryName: String) {
        disposable?.dispose()
        repoListLiveData.value = Loading

        disposable = gitHubRestApi.fetchRepositoryList(repositoryName)
            .map { GitRepoResponseAdapter.map(it) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ repoListLiveData.value = it }, {
                //Handle the error properly
                Timber.e(it)
                repoListLiveData.value = GitRepoResponseAdapter.map(it)
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        disposable = null
    }
}