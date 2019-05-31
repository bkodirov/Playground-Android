package git.playground.android.di

import dagger.Component
import git.playground.android.ui.MainActivity
import git.playground.android.viewmodel.FlickrPhotoFetcherViewModel

@Component(modules = [ MainModule::class])
interface MainComponent {
    fun inject(viewModel: FlickrPhotoFetcherViewModel)
    fun inject(activity: MainActivity)
}