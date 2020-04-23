package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThrillerModel(
    val thumb: String,
    val video: String

): Parcelable

