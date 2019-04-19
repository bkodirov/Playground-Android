package git.playground.android.datalayer

import android.app.Application
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import git.playground.android.datalayer.api.ApiModule
import git.playground.android.datalayer.date.InstantAdapter
import okhttp3.OkHttpClient

@Module(includes = [ApiModule::class])
class DataModule {

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