package git.playground.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import git.playground.android.ImmediateSchedulerProvider
import git.playground.android.datalayer.api.FlickrRestApi
import git.playground.android.ui.Fail
import git.playground.android.ui.Loading
import git.playground.android.ui.PhotoSearchUiState
import git.playground.android.ui.Success
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.threeten.bp.Instant

class FlickrPhotoFetcherViewModelTest {
    private lateinit var viewModel: FlickrPhotoFetcherViewModel
    private val flickrRestApi: FlickrRestApi = mock()
    private val liveDataMock: MutableLiveData<PhotoSearchUiState> = mock()
    @Before
    fun setUp() {
        viewModel = FlickrPhotoFetcherViewModel()
        viewModel.schedulerProvider = ImmediateSchedulerProvider
        viewModel.photoSearchresultLiveData = liveDataMock
        viewModel.flickrRestApi = flickrRestApi
    }

    @Test
    fun `given GitHubService emitting Throwable WHEN fetchRepositoryList called THEN Fail type sent to liveData`() {
        val searchTerm = "search_term"
        whenever(flickrRestApi.fetchRepositoryList(searchTerm)).thenReturn(Single.error(RuntimeException("Something went wrong")))
        viewModel.searchPhotos(searchTerm)
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
        whenever(flickrRestApi.fetchRepositoryList(searchTerm)).thenReturn(Single.just(listOf(fixture)))
        viewModel.searchPhotos(searchTerm)
        verify(liveDataMock).value = Loading
        verify(liveDataMock).value = isA<Success>()
    }
}