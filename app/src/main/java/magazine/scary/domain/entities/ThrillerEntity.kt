package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThrillerEntity(
    val thumb: String,
    val video: String

): Parcelable

