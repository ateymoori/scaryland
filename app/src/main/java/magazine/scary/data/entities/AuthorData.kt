package magazine.scary.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorData(
    val id: Int,
    val name_family: String,
    val description: String? = null,
    val image: String
) : Parcelable
