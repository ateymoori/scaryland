package magazine.scary.data.repositories.image

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.data.mappers.ImageDataEntityMapper
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.interactors.ImageRepository

class ImagesRemoteRepository
constructor(
    private val api: Api
) : ImageRepository {

    private val imageMapper = ImageDataEntityMapper()

    override fun getImages(): Observable<List<ImageEntity>> {
        return api.getImages().map { results ->
            results.map { imageMapper.mapFrom(it) }
        }
    }

}