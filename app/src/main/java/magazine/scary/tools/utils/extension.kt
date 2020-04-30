package magazine.scary.tools.utils

import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.entities.PosterEntity

fun PosterEntity.mapToImageModel(): ImageEntity {

    return ImageEntity(
        largeImageURL = file_path
    )
}