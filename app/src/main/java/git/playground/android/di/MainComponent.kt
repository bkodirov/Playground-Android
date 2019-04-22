package git.playground.android.di

import dagger.Component
import git.playground.android.ui.MainActivity
import git.playground.android.viewmodel.GitRepoViewModel

@Component(modules = [ MainModule::class])
interface MainComponent {
    fun inject(viewModel: GitRepoViewModel)
    fun inject(activity: MainActivity)
}