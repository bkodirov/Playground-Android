package git.playground.android.datalayer.api

import dagger.Module
import dagger.Provides
import git.playground.android.datalayer.DataModule
import git.playground.android.domain.model.Repository
import io.reactivex.Single
import okhttp3.HttpUrl

@Module
//class ApiModule(private val dataModule: DataModule) {
class ApiModule {
    private val PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com/")

//    @Provides
//    fun baseUrlProvider():HttpUrl {
//        return PRODUCTION_API_URL!!
//    }

//    @Provides
//    fun provideRetrofit(baseUrl: HttpUrl, client: OkHttpClient, moshi: Moshi): Retrofit {
//        return Retrofit.Builder() //
//            .client(client) //
//            .baseUrl(baseUrl) //
//            .addConverterFactory(MoshiConverterFactory.create(moshi)) //
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
//            .build()
//    }
//
//    @Provides
//    fun provideGithubService(retrofit: Retrofit): GithubService {
//        return retrofit.create<GithubService>(GithubService::class.java)
//    }
    @Provides
    fun provideGithubService(): GithubService {
        return object:GithubService {
            override fun fetchRepositoryList(org: String): Single<List<Repository>> {
                return Single.error<List<Repository>>(NullPointerException("DUMMY dummy"))
            }
        }
    }
}