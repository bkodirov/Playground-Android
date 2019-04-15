package git.playground.android.datalayer.api

import git.playground.android.domain.model.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/orgs/{ORGANIZATION}/repos")
    fun fetchRepositoryList(@Path("ORGANIZATION") org: String): Single<List<Repository>>
}