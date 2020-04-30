package magazine.scary.data.mappers

import magazine.scary.data.entities.ImageData
import magazine.scary.domain.common.Mapper
import magazine.scary.data.entities.ThrillerData
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.entities.ThrillerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageDataEntityMapper @Inject constructor() : Mapper<ImageData, ImageEntity>() {
    override fun mapFrom(from: ImageData): ImageEntity {
        return ImageEntity(
            id = from.id,
            tags = from.tags,
            previewURL = from.previewURL,
            largeImageURL = from.largeImageURL,
            imageSize = from.imageSize,
            views = from.views,
            favorites = from.favorites,
            likes = from.likes,
            user = from.user,
            userImageURL = from.userImageURL
        )
    }
}
