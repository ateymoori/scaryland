package magazine.scary.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "images")
data class ImageModel(
    val favorites: Int? = null,
    @PrimaryKey
    val id: Int? = null,
    val imageSize: Int? = null,
    val largeImageURL: String? = null,
    val likes: Int? = null,
    val previewURL: String? = null,
    val user: String? = null,
    val userImageURL: String? = null,
    val views: Int? = null,
    val webformatURL: String? = null
) : Parcelable