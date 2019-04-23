package git.playground.android.di

import com.jakewharton.threetenabp.AndroidThreeTen
import git.playground.android.PlaygroundApplication
import git.playground.android.datalayer.api.ApiModule

object DepGraph {
    private var application: PlaygroundApplication? = null
    var mainComponent: MainComponent? = null


    fun init(application: PlaygroundApplication) {
        this.application = application
        mainComponent = DaggerMainComponent.builder()
            .mainModule(MainModule(application))
            .apiModule(ApiModule(application))
            .build()
        AndroidThreeTen.init(application)
    }
}