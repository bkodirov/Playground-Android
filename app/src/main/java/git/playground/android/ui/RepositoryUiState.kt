package git.playground.android.ui

import git.playground.android.domain.model.Repository
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class RepositoryUiState
object Loading:RepositoryUiState()
data class Fail(val message:String):RepositoryUiState()
data class Success(val repoList: List<Repository>):RepositoryUiState()

class Adapter {
    companion object {
        fun map(t:Throwable):RepositoryUiState {
            return when (t) {
                is UnknownHostException -> Fail("Check your internet connection")
                is HttpException -> Fail("Http Error")
                else -> Fail("Unknown error")
            }
        }
        fun map(list:List<Repository>):RepositoryUiState {
            return Success(list)
        }
    }
}
