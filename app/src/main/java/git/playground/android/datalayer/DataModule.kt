package git.playground.android.datalayer

import dagger.Module
import dagger.Provides
import git.playground.android.datalayer.api.ApiModule
import okhttp3.OkHttpClient

@Module(includes = [ApiModule::class])
class DataModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return createOkHttpClient().build()
    }

    fun createOkHttpClient(): OkHttpClient.Builder {
        // Install an HTTP cache in the application cache directory.
        return OkHttpClient.Builder()
    }

    @Provides
    fun provideTimeFormat(): String {
        return "YYYY-MM-DDTHH:MM:SSZ"
    }
}