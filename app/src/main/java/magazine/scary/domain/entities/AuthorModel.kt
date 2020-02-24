package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorModel(
    val ___class: String,
    val author_id: Int,
    val created: Long,
    val description: String,
    val image: String,
    val name: String,
    val objectId: String,
    val ownerId: String?,
    val updated: Long
): Parcelable