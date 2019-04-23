package testsupport

import android.content.Context

object TestSupport {
    fun readAssetAsString(fileName: String, context: Context): String {
        return context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}