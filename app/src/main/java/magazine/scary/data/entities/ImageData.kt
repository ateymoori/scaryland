package magazine.scary.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageData(
    @PrimaryKey
    val id: Int? = null,
    val tags: String? = null,
    val previewURL: String? = null,
    val largeImageURL: String? = null,
    val imageSize: String? = null,
    val views: Int? = null,
    val favorites: Int? = null,
    val likes: Int? = null,
    val user: String? = null,
    val userImageURL: String? = null
) : Parcelable
