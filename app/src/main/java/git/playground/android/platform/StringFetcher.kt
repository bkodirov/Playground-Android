package git.playground.android.platform

import android.content.res.Resources
import androidx.annotation.StringRes

class StringFetcher(private val resource: Resources) {
    fun getString(@StringRes resId: Int, vararg params: Any): String {
        return resource.getString(resId, *params)
    }
}