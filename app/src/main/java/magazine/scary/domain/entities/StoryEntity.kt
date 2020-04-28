package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StoryEntity(
    val id: Int,
    val title: String,
    val content: String? = null,
    val image: String,
    val mp3_file: String? = null,
    val reading_minutes: Long?,
    val author_id:Int? ,
    val author_name_family:String? ,
    val author_description:String? ,
    val author_image:String?
) : Parcelable




