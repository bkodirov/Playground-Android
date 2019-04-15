package git.playground.android.datalayer

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class DataModule (private val app: Application){

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient {
        return createOkHttpClient(app).build()
    }

    fun createOkHttpClient(app: Application): OkHttpClient.Builder {
        // Install an HTTP cache in the application cache directory.
        return OkHttpClient.Builder()
    }

    @Provides
    fun provideTimeFormat(): String {
        return "YYYY-MM-DDTHH:MM:SSZ"
    }
}