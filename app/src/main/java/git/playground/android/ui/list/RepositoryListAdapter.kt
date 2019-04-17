package git.playground.android.ui.list

import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import git.playground.android.domain.model.Repository

typealias ClickHandler = (Int, Repository) -> Unit
class RepositoryListAdapter(private val list: List<Repository>) : RecyclerView.Adapter<RepositoryViewHolder>() {
    var itemClickListener: ClickHandler? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(Button(parent.context))
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {

    }
}