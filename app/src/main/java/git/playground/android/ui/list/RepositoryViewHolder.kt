package git.playground.android.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import git.playground.android.R
import git.playground.android.ui.UiPhotoCellDto
import kotlinx.android.synthetic.main.layout_repository_list_item.view.*
import timber.log.Timber


class RepositoryViewHolder(
    private val view: View,
    private val itemClickListener: ClickHandler?
) : RecyclerView.ViewHolder(view) {
    private var repository: UiPhotoCellDto? = null

    init {
        view.setOnClickListener { _ ->
            repository?.let { itemClickListener?.invoke(it) }
        }
    }


    fun bind(data: UiPhotoCellDto) {
        this.repository = data
        with(view) {
            Timber.d("Going to glide image at the url ${data.url}")
            tvPhotoTitle.text = data.title
            Glide.with(imageViewPhoto).load(data.url)
                .placeholder(R.mipmap.placeholder)
                .into(imageViewPhoto)
        }
    }
}
