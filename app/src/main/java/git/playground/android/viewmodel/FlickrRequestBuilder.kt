package git.playground.android.viewmodel

import git.playground.android.BuildConfig
import javax.inject.Inject

private const val KEY_METHOD = "method"
private const val VAL_METHOD = "flickr.photos.search"
private const val KEY_API_KEY = "api_key"
private const val VAL_API_KEY = BuildConfig.API_KEY_FLICKR
private const val KEY_FORMAT = "format"
private const val VAL_FORMAT = "json"
private const val KEY_JSONCALLBACK = "nojsoncallback"
private const val VAL_JSONCALLBACK = "1"
private const val KEY_TEXT = "text"


class FlickrRequestBuilder @Inject constructor(){
    fun createSearchRequest(searchTerm: String): Map<String, String> {
        return mapOf(
            KEY_METHOD to VAL_METHOD,
            KEY_API_KEY to VAL_API_KEY,
            KEY_FORMAT to VAL_FORMAT,
            KEY_JSONCALLBACK to VAL_JSONCALLBACK,
            KEY_TEXT to searchTerm
        )
    }
}
