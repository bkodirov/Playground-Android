package git.playground.android.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import git.playground.android.R
import git.playground.android.domain.model.Repository
import git.playground.android.platform.StringFetcher
import kotlinx.android.synthetic.main.layout_repository_list_item.view.*
import org.threeten.bp.format.DateTimeFormatter


class RepositoryViewHolder(
    private val view: View,
    private val stringFetcher: StringFetcher,
    private val dateFormatter: DateTimeFormatter,
    private val itemClickListener: ClickHandler?
) : RecyclerView.ViewHolder(view) {
    private var repository: Repository? = null

    init {
        view.setOnClickListener { _ ->
            repository?.let { itemClickListener?.invoke(it) }
        }
    }


    fun bind(repo: Repository) {
        this.repository = repo
        with(view) {
            tvRepoName.text = stringFetcher.getString(R.string.template_repo, repo.name)
            repo.description?.let { tvDescription.text = stringFetcher.getString(R.string.template_description, it) }
            tvCreatedAt.text =
                    stringFetcher.getString(R.string.template_created_at, dateFormatter.format(repo.createdAt))
            tvUpdatedAt.text =
                    stringFetcher.getString(R.string.template_updated_at, dateFormatter.format(repo.updatedAt))
            tvLanguage.text = stringFetcher.getString(R.string.template_language, repo.language)
            tvStatus.text = stringFetcher.getString(R.string.template_status, repo.status())
        }
    }
}
