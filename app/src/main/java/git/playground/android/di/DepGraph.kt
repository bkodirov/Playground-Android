package git.playground.android.di

import com.jakewharton.threetenabp.AndroidThreeTen
import git.playground.android.PlaygroundApplication
import git.playground.android.ui.MainActivity
import git.playground.android.viewmodel.GitRepoViewModel

object DepGraph: MainComponent {
    private var application: PlaygroundApplication? = null
    private var mainComponent: MainComponent? = null

    override fun inject(viewModel: GitRepoViewModel) {
        mainComponent?.inject(viewModel)
    }

    override fun inject(activity: MainActivity) {
        mainComponent?.inject(activity)
    }

    fun init(application: PlaygroundApplication) {
        this.application = application
        mainComponent = DepGraph.application?.let {
            DaggerMainComponent.builder()
                .mainModule(MainModule(it))
                .build()
        } ?: throw IllegalStateException("Graph has not been initialized")
        AndroidThreeTen.init(application)
    }
}