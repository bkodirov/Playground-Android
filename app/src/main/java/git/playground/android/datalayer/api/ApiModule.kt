package git.playground.android.datalayer.api

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import git.playground.android.datalayer.date.InstantAdapter
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
//class ApiModule(private val dataModule: DataModule) {
open class ApiModule(private val context: Context) {
    private val PRODUCTION_API_URL = HttpUrl.parse("https://api.flickr.com/")

    @Provides
    fun baseUrlProvider(): HttpUrl {
        return PRODUCTION_API_URL!!
    }

    @Provides
    fun provideRetrofit(baseUrl: HttpUrl, client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder() //
            .client(client) //
            .baseUrl(baseUrl) //
            .addConverterFactory(MoshiConverterFactory.create(moshi)) //
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
            .build()
    }

    @Provides
    open fun provideGithubService(retrofit: Retrofit): FlickrRestApi {
        return retrofit.create<FlickrRestApi>(FlickrRestApi::class.java)
    }


    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(InstantAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}