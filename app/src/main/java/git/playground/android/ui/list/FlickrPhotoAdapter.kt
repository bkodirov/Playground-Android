package git.playground.android.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import git.playground.android.R
import git.playground.android.platform.StringFetcher
import git.playground.android.ui.UiPhotoCellDto
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

typealias ClickHandler = (UiPhotoCellDto) -> Unit

class FlickrPhotoAdapter(private val list: List<UiPhotoCellDto>) : RecyclerView.Adapter<RepositoryViewHolder>() {
    var itemClickListener: ClickHandler? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.layout_repository_list_item, parent, false)
        return RepositoryViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(list[position])
    }
}