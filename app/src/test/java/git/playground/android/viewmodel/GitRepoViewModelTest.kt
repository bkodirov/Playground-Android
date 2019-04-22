package git.playground.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import git.playground.android.ImmediateSchedulerProvider
import git.playground.android.datalayer.api.GitHubRestApi
import git.playground.android.domain.model.Repository
import git.playground.android.ui.Fail
import git.playground.android.ui.Loading
import git.playground.android.ui.RepositoryUiState
import git.playground.android.ui.Success
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.threeten.bp.Instant

class GitRepoViewModelTest {
    private lateinit var viewModel: GitRepoViewModel
    private val gitHubRestApi: GitHubRestApi = mock()
    private val liveDataMock: MutableLiveData<RepositoryUiState> = mock()
    @Before
    fun setUp() {
        viewModel = GitRepoViewModel()
        viewModel.schedulerProvider = ImmediateSchedulerProvider
        viewModel.repoListLiveData = liveDataMock
        viewModel.gitHubRestApi = gitHubRestApi
    }

    @Test
    fun `given GitHubService emitting Throwable WHEN fetchRepositoryList called THEN Fail type sent to liveData`() {
        val searchTerm = "search_term"
        whenever(gitHubRestApi.fetchRepositoryList(searchTerm)).thenReturn(Single.error(RuntimeException("Something went wrong")))
        viewModel.searchRepository(searchTerm)
        verify(liveDataMock).value = Loading
        verify(liveDataMock).value = isA<Fail>()
    }

    @Test
    fun `given GitHubService emiting list of Repository objects WHEN fetchRepositoryList called THEN Success type sent to liveData`() {
        val searchTerm = "search_term"
        val fixture = Repository(
            12,
            "name",
            "full_name",
            null,
            "http://something.com/",
            Instant.now(),
            Instant.now(),
            "swift",
            false,
            false,
            "master"
        )
        whenever(gitHubRestApi.fetchRepositoryList(searchTerm)).thenReturn(Single.just(listOf(fixture)))
        viewModel.searchRepository(searchTerm)
        verify(liveDataMock).value = Loading
        verify(liveDataMock).value = isA<Success>()
    }
}