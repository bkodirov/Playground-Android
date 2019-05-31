package git.playground.android.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import git.playground.android.platform.PlatformSpecificUtils
import git.playground.android.ui.PhotoSearchUiState
import git.playground.android.ui.SearchViewDelegate

@Module
class SearchModuleModule {
    @Provides
    fun provideMutableLiveData() : MutableLiveData<PhotoSearchUiState> {
        return MutableLiveData<PhotoSearchUiState>()
    }
    @Provides
    fun provideSearchViewDelegate(platformSpecificUtils: PlatformSpecificUtils): SearchViewDelegate {
        return SearchViewDelegate(platformSpecificUtils)
    }
}