package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import magazine.scary.domain.entities.ImageModel

@Parcelize
data class ResponseModel(
    val hits: List<ImageModel>,
    val total: Int,
    val totalHits: Int
): Parcelable