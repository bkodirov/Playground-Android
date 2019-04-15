package git.playground.android.ui

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

class ChromeTabsDelegate (private val context: Context){
    fun launchTab(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}
