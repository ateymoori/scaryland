package magazine.scary.tools

import magazine.scary.domain.entities.ImageModel
import magazine.scary.domain.entities.PosterEntity

fun PosterEntity.mapToImageModel(): ImageModel {

    return ImageModel(
        largeImageURL = file_path
    )
}