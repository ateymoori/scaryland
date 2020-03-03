package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StoryModel(
    val ___class: String,
    val content_file: String,
    val created: Long,
    val image: String,
    val objectId: String,
    val ownerId: String?,
    val title: String,
    val author: AuthorModel?,
    val updated: Long
): Parcelable