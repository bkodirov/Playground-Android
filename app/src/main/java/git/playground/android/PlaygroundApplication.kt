package git.playground.android

import android.app.Application
import git.playground.android.di.DepGraph
import timber.log.Timber

class PlaygroundApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        DepGraph.init(this)
    }
}