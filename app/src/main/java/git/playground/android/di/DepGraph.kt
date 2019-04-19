package git.playground.android.di

import com.jakewharton.threetenabp.AndroidThreeTen
import git.playground.android.PlaygroundApplication
import git.playground.android.datalayer.DataModule
import git.playground.android.datalayer.api.ApiModule

object DepGraph {
    private var application: PlaygroundApplication? = null

    fun init(application: PlaygroundApplication) {
        this.application = application
        AndroidThreeTen.init(application)
    }

    val component: MainComponent by lazy {
        application?.let {
            DaggerMainComponent.builder()
                .mainModule(MainModule(it))
                .build()
        } ?: throw IllegalStateException("Graph has not been initialized")
    }
}