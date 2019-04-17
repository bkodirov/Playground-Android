package git.playground.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import git.playground.android.R
import git.playground.android.domain.model.Repository
import git.playground.android.ui.list.RepositoryListAdapter
import git.playground.android.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import timber.log.Timber

class RepositoryListFragment : Fragment() {
    companion object {
        fun newInstance(): RepositoryListFragment {
            return RepositoryListFragment()
        }
    }
    private val list = mutableListOf<Repository>()
    private val repositoryAdapter = RepositoryListAdapter(list)
    private lateinit var chromeTabDelegate:ChromeTabsDelegate


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initRecyclerView()
        listenToRepositories()
        activity?.let {
            chromeTabDelegate = ChromeTabsDelegate(it)
        }
    }

    private fun initRecyclerView() {
        val viewManager = LinearLayoutManager(context)

        repositoryRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = repositoryAdapter
        }
        repositoryAdapter.itemClickListener =  { pos:Int, repo:Repository ->

        }
    }

    private fun listenToRepositories() {
        val localActivityRef = activity
        localActivityRef ?: return
        val repositoryViewModel = ViewModelProviders.of(localActivityRef).get(RepositoryViewModel::class.java)
        repositoryViewModel.getRepositories().observe(this, Observer<RepositoryUiState> {
            // update UI
            Timber.d("## REPOS received- > $it")
            when (it) {
                is Success -> {
//                    hideLoader()
                    list.clear()
                    list.addAll(it.repoList)
                    repositoryAdapter.notifyDataSetChanged()
                }
                is Fail-> {
//                    hideLoader()
//                    showErrorMessage()
                }
                is Loading-> {
//                    showLoader()
                }

            }
        })
    }
}