package git.playground.android.datalayer.api

import android.content.Context
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
import testsupport.mockApiResponse
import timber.log.Timber

@Module
class TestApiModule(private val context: Context) : ApiModule(context) {

    @Provides
    override fun provideGithubService(retrofit: Retrofit): FlickrRestApi {
        return object : FlickrRestApi {
            override fun fetchRepositoryList(ignored: String): Single<List<Repository>> {
                return Single.create { emitter ->
                    val type = Types.newParameterizedType(List::class.java, Repository::class.java)
                    val adapter = provideMoshi().adapter<List<Repository>>(type)
                    Timber.d("## Search. call-> start parsing")
                    adapter.fromJson(mockApiResponse)?.let {
                        Timber.d("## Search. parsing done")
                        emitter.onSuccess(it)
                    } ?: emitter.onError(
                        IllegalArgumentException("Invalid json provided")
                    )
                }
            }
        }
    }
}