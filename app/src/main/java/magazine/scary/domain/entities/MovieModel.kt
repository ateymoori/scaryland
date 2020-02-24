package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieModel(
    val ___class: String,
    val created: Long,
    val description: String,
    val director: String,
    val genre: String,
    val name: String,
    val objectId: String,
    val ownerId: String?,
    val rate: String,
    val small_image: String,
    val thriller: String?,
    val updated: Long,
    val wide_image: String,
    val year: Int
): Parcelable