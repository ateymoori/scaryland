package magazine.scary.domain.entities

data class Result(
    val content_en: String,
    val createdAt: String,
    val objectId: String,
    val small_image: SmallImage,
    val title_en: String,
    val updatedAt: String
)