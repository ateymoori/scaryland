package magazine.scary.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThrillerData(
    val thumb: String,
    val video: String

): Parcelable

