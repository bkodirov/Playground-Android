package git.playground.android.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import git.playground.android.R
import git.playground.android.di.DepGraph
import git.playground.android.viewmodel.GitRepoViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var model:GitRepoViewModel
    @Inject lateinit var searchViewDelegat:SearchViewDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(GitRepoViewModel::class.java)
        supportFragmentManager.beginTransaction().add(R.id.activity_container, RepositoryListFragment.newInstance()).commitAllowingStateLoss()
    }

    private fun initInjection() {
        DepGraph.mainComponent?.inject(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.app_bar_search) // get my MenuItem with placeholder submenu
        searchMenuItem?.let { searchViewDelegat.onCreateOptionMenu(it, model) }
        return true
    }
}
