package magazine.scary.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "stories")
data class StoryData(
    @PrimaryKey
    val id: Int,
    val title: String,
    val author: AuthorData?,
    val content: String? = null,
    val image: String,
    val mp3_file: String? = null,
    val reading_mintues: Long?
) : Parcelable




