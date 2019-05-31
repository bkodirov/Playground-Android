package git.playground.android.ui

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.nhaarman.mockitokotlin2.*
import git.playground.android.platform.PlatformSpecificUtils
import git.playground.android.viewmodel.FlickrPhotoFetcherViewModel
import net.lachlanmckee.timberjunit.TimberTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewDelegateTest {
    @get:Rule val timberRule = TimberTestRule.logAllWhenTestFails()

    private lateinit var searchViewDelegate: SearchViewDelegate
    private val viewModelMock: FlickrPhotoFetcherViewModel = mock()
    private lateinit var onQueryTextListener: SearchView.OnQueryTextListener

    private var utilsMock = mock<PlatformSpecificUtils>()
    private val searchViewMock: SearchView = mock()

    @Before
    fun setUp() {

        val menuItemMock: MenuItem = mock()
        whenever(menuItemMock.actionView).thenReturn(searchViewMock)

        searchViewDelegate = SearchViewDelegate(utilsMock)
        searchViewDelegate.onCreateOptionMenu(menuItemMock, viewModelMock)

        argumentCaptor<SearchView.OnQueryTextListener>().apply {
            verify(searchViewMock).setOnQueryTextListener(capture())
            onQueryTextListener = firstValue
        }
    }

    @Test
    fun `when user typed empty spaces THEN viewModel's methods won't be invoked`() {
        onQueryTextListener.onQueryTextSubmit(" ")
        verifyNoMoreInteractions(viewModelMock)
    }
    @Test
    fun `when null passed to onQueryTextSubmit THEN viewModel's methods won't be invoked`() {
        onQueryTextListener.onQueryTextSubmit(null)
        verifyNoMoreInteractions(viewModelMock)
    }
    @Test
    fun `when non empty string passed to onQueryTextSubmit THEN viewModel's searchRepository methods is invoked`() {
        val expected = "search term"
        onQueryTextListener.onQueryTextSubmit(expected)
        verify(viewModelMock).searchPhotos(expected)
    }
    @Test
    fun `when non empty string passed to onQueryTextSubmit THEN utils#hideKeyboard is invoked with searchView`() {
        onQueryTextListener.onQueryTextSubmit("search term")
        verify(utilsMock).hideKeyboard(searchViewMock)
    }
}