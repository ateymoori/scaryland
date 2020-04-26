package magazine.scary.domain.entities

data class PosterModel(
    val aspect_ratio: Double,
    val file_path: String?,
    val height: Int?,
    val iso_639_1: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)

fun PosterModel.mapToImageModel(): ImageModel {

    return ImageModel(
        largeImageURL = file_path
    )
}