package magazine.scary.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StoryData(
    val id: Int,
    val title: String,
    val author: AuthorData?,
    val content: String? = null,
    val image: String,
    val mp3_file: String? = null,
    val reading_mintues: Long?
) : Parcelable




