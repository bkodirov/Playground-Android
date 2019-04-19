package git.playground.android.domain.model

import com.squareup.moshi.Json
import org.threeten.bp.Instant

data class Repository(
    val id: Int,
    val name: String,
    @Json(name = "full_name") val fullName: String,
    val description: String?,
    val url: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "collaborators_url") val collaboratorsUrl: String,
    @Json(name = "tags_url") val tagsUrl: String,
    @Json(name = "created_at") val createdAt: Instant,
    @Json(name = "updated_at") val updatedAt: Instant?,
    val language: String,
    val archived: Boolean,
    val disabled: Boolean,
    @Json(name = "default_branch") val defaultBranch: String
) {
    fun status(): String {
        return if (archived) "Archived" else "Active"
    }
}
