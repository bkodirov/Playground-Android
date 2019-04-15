package git.playground.android.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import git.playground.android.domain.model.Repository

typealias ClickHandler = (Int, Repository) -> Unit
class RepositoryListAdapter(private val list: List<Repository>) : RecyclerView.Adapter<RepositoryViewHolder>() {
    var itemClickListener: ClickHandler? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}