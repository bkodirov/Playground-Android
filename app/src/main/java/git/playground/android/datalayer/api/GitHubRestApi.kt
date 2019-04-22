package git.playground.android.datalayer.api

import git.playground.android.domain.model.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRestApi {
    @GET("orgs/{ORGANIZATION}/repos")
    fun fetchRepositoryList(@Path("ORGANIZATION") org: String): Single<List<Repository>>
}
