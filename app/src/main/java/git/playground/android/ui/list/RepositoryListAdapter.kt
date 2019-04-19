package git.playground.android.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import git.playground.android.R
import git.playground.android.domain.model.Repository
import git.playground.android.platform.StringFetcher
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

typealias ClickHandler = (Repository) -> Unit

class RepositoryListAdapter(private val list: List<Repository>) : RecyclerView.Adapter<RepositoryViewHolder>() {
    var itemClickListener: ClickHandler? = null
    private var dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        .withLocale(Locale.UK)
        .withZone(ZoneId.systemDefault())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.layout_repository_list_item, parent, false)
        val stringFetcher = StringFetcher(parent.resources)
        return RepositoryViewHolder(view, stringFetcher, dateFormatter, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(list[position])
    }
}