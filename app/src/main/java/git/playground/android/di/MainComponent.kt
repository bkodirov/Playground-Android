package git.playground.android.di

import dagger.Component
import git.playground.android.datalayer.DataModule
import git.playground.android.datalayer.api.ApiModule
import git.playground.android.ui.MainActivity
import git.playground.android.viewmodel.RepositoryViewModel

@Component(modules = [ MainModule::class, DataModule::class, ApiModule::class ])
interface MainComponent {
    fun inject(presenter: RepositoryViewModel)
    fun inject(presenter: MainActivity)
}