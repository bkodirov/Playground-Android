package git.playground.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import git.playground.android.datalayer.api.GithubService
import git.playground.android.di.DepGraph
import git.playground.android.domain.model.Repository
import git.playground.android.ui.RepositoryUiState
import git.playground.android.ui.Status
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryViewModel : ViewModel() {

    @Inject lateinit var githubService: GithubService
    private val repositories = MutableLiveData<RepositoryUiState>()
    @Volatile private var disposable:Disposable?=null

    init {
        DepGraph.component.inject(this)
    }

    fun getRepositories(): LiveData<RepositoryUiState> {
        return repositories
    }

    fun searchRepository(repositoryName: String) {
        disposable?.dispose()
        repositories.value = RepositoryUiState(status = Status.LOADING)

        disposable = githubService.fetchRepositoryList(repositoryName)
            .map { RepositoryUiState.map(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({ repositories.value = it }, {
                //Handle the error properly
                repositories.value = RepositoryUiState.map(it)
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        disposable = null
    }
}