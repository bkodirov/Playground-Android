package git.playground.android.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import git.playground.android.platform.PlatformSpecificUtils
import git.playground.android.ui.RepositoryUiState
import git.playground.android.ui.SearchViewDelegate

@Module
class GitRepoModule {
    @Provides
    fun provideMutableLiveData() : MutableLiveData<RepositoryUiState> {
        return MutableLiveData<RepositoryUiState>()
    }
    @Provides
    fun provideSearchViewDelegate(platformSpecificUtils: PlatformSpecificUtils): SearchViewDelegate {
        return SearchViewDelegate(platformSpecificUtils)
    }
}