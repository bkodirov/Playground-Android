package git.playground.android.di

import git.playground.android.PlaygroundApplication

object DepGraph {
    private var application: PlaygroundApplication? = null

    fun init(application: PlaygroundApplication) {
        this.application = application
    }

    val component: MainComponent by lazy {
        application?.let {
            DaggerMainComponent.builder()
                .mainModule(MainModule(it))
                .build()
        } ?: throw IllegalStateException("Graph has not been initialized")
    }
}