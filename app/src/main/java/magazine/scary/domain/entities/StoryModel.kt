package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StoryModel(
    val id: Long,
    val title: String,
    val author: AuthorModel?,
    val content: String? = null,
    val image: String,
    val mp3_file: String? = null,
    val reading_mintues: Long?
) : Parcelable




