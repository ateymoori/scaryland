package magazine.scary.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val title: String,
    val portrait_image: String,
    val landscape_image: String,
    val imdb: Float,
    val created_at: String,
    val release_date: String,
    val overview: String,
    val runetime: String,
    val genres: String,
    val awards: String,
    val rotten_tomato: String,
    val themoviedb_id: Long?
): Parcelable

