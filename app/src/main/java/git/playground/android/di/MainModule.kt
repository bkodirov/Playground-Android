package git.playground.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import git.playground.android.datalayer.DataModule
import git.playground.android.domain.SchedulerProvider
import git.playground.android.domain.SchedulerProviderImpl
import git.playground.android.platform.PlatformSpecificUtils
import git.playground.android.viewmodel.SearchModuleModule

@Module(includes = [DataModule::class, SearchModuleModule::class])
class MainModule(private val context: Context) {
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }

    @Provides
    fun rxSchedulerProvider(): SchedulerProvider {
        return SchedulerProviderImpl
    }

    @Provides
    fun providePlatformSpecificUtils(): PlatformSpecificUtils {
        return PlatformSpecificUtils()
    }
}