package git.playground.android.domain.model

import com.squareup.moshi.Json

data class PhotosResponse(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String,
    @Json(name = "photo") val  photoList: List<FlickrPhoto>
)
