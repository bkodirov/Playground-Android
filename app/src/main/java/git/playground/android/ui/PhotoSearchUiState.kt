package git.playground.android.ui

import git.playground.android.domain.model.FlickrPhoto
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

sealed class PhotoSearchUiState
object Loading : PhotoSearchUiState()
data class Fail(val message: String) : PhotoSearchUiState()
data class Success(val repoList: List<UiPhotoCellDto>) : PhotoSearchUiState()

class PhotoListResponseAdapter {
    companion object {
        fun map(t: Throwable): PhotoSearchUiState {
            return when (t) {
                is UnknownHostException -> Fail("Check your internet connection")
                is HttpException -> Fail("Http Error")
                else -> Fail("Unknown error")
            }
        }
    }
}

data class UiPhotoCellDto(val title: String, val url: String)

class UiPhotoDtoGenerator @Inject constructor() {
    fun generate(apiResponse: FlickrPhoto): UiPhotoCellDto {
        val imageUrl = "https://farm${apiResponse.farm}.staticflickr.com/${apiResponse.server}/${apiResponse.id}_${apiResponse.secret}.jpg"
        val len = if (apiResponse.title.length < 10) {
            apiResponse.title.length
        } else {
            10
        }
        val trimmedTitle = apiResponse.title.substring(0, len)
        return UiPhotoCellDto(trimmedTitle, imageUrl)
    }
}