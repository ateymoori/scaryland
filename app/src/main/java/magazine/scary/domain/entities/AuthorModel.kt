package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorModel(
    val id: Long,
    val name_family: String,
    val description: String? = null,
    val image: String
) : Parcelable
