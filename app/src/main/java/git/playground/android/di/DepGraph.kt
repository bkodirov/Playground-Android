package git.playground.android.di

import git.playground.android.PlaygroundApplication
import git.playground.android.datalayer.DataModule
import git.playground.android.datalayer.api.ApiModule

object DepGraph {
    private var application: PlaygroundApplication? = null

    fun init(application: PlaygroundApplication) {
        this.application = application

    }

    val component: MainComponent by lazy {
        application?.let {
            DaggerMainComponent.builder()
                .apiModule(ApiModule())
                .build()
        } ?: throw IllegalStateException("Graph has not been initialized")
    }
}