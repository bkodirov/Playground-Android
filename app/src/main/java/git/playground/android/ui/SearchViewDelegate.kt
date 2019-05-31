package git.playground.android.ui

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import git.playground.android.platform.PlatformSpecificUtils
import git.playground.android.viewmodel.FlickrPhotoFetcherViewModel
import javax.inject.Inject

class SearchViewDelegate @Inject constructor(private val utils: PlatformSpecificUtils){

    fun onCreateOptionMenu(searchMenu: MenuItem, viewModel: FlickrPhotoFetcherViewModel) {
        searchMenu.expandActionView()
        val searchView = searchMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { if (it.isNotBlank()) viewModel.searchPhotos(it) }
                utils.hideKeyboard(searchView)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {return true}
        })
    }
}