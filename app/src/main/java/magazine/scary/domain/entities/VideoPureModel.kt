package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class VideoPureModel(
    val url: String? = null ,
    val title: String? = null
): Parcelable