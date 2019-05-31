package git.playground.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import git.playground.android.datalayer.api.FlickrRestApi
import git.playground.android.di.DepGraph
import git.playground.android.domain.SchedulerProvider
import git.playground.android.ui.*
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class FlickrPhotoFetcherViewModel : ViewModel() {

    @Inject lateinit var flickrRestApi: FlickrRestApi
    @Inject lateinit var schedulerProvider: SchedulerProvider
    @Inject lateinit var photoSearchresultLiveData: MutableLiveData<PhotoSearchUiState>
    @Inject lateinit var flickrRequestBuilder: FlickrRequestBuilder
    @Inject lateinit var uiPhotoDtoGenerator: UiPhotoDtoGenerator

    @Volatile
    private var disposable: Disposable? = null

    init {
        DepGraph.mainComponent?.inject(this)
    }

    fun getPhotos(): LiveData<PhotoSearchUiState> {
        return photoSearchresultLiveData
    }

    fun searchPhotos(searchTerm: String) {
        Timber.d("## Search. started")
        disposable?.dispose()
        photoSearchresultLiveData.value = Loading
        Timber.d("## Search. call-> flickrRestApi.fetchRepositoryList()")

        disposable = flickrRestApi.fetchPhotos(flickrRequestBuilder.createSearchRequest(searchTerm))
            .map { it.photos.photoList }
            .toObservable()
            .flatMapIterable { it }
            .map { uiPhotoDtoGenerator.generate(it) }
            .toList()
            .map { Success(it) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                Timber.d("## Search. call-> PhotoFetcher view model.total=$it")
                photoSearchresultLiveData.value = it },
                {
                //Handle the error properly
                Timber.e(it)
                photoSearchresultLiveData.value = PhotoListResponseAdapter.map(it)
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        disposable = null
    }
}