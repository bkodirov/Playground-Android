package git.playground.android.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import git.playground.android.R
import git.playground.android.di.DepGraph
import git.playground.android.viewmodel.RepositoryViewModel
import okhttp3.HttpUrl
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val searchViewDelegat = SearchViewDelegate()
    private lateinit var model:RepositoryViewModel
@Inject
lateinit var url: HttpUrl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        initInjection()
        supportFragmentManager.beginTransaction().add(R.id.activity_container, RepositoryListFragment.newInstance()).commitAllowingStateLoss()
    }

    private fun initInjection() {
        DepGraph.component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        Timber.d(url.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.app_bar_search) // get my MenuItem with placeholder submenu
        searchMenuItem?.let { searchViewDelegat.onCreateOptionMenu(it, model) }
        return true
    }
}
