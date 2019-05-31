package git.playground.android.datalayer.api

import git.playground.android.domain.model.PhotoSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FlickrRestApi {

    @GET("services/rest/")
    fun fetchPhotos(@QueryMap queryMap: Map<String, String>): Single<PhotoSearchResult>

}
