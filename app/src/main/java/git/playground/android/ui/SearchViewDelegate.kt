package git.playground.android.ui

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import git.playground.android.platform.Utils
import git.playground.android.viewmodel.RepositoryViewModel

class SearchViewDelegate {

    fun onCreateOptionMenu(searchMenu: MenuItem, viewModel: RepositoryViewModel) {
        searchMenu.expandActionView()
        val searchView = searchMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { if (it.isNotBlank()) viewModel.searchRepository(it) }
                Utils.hideKeyboard(searchView)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {return true}
        })
    }
}