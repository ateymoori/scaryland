package magazine.scary.data.repositories.image

import io.reactivex.Observable
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.interactors.ImageRepository

class ImageRepositoryImpl
constructor(
    private val imagesRemoteRepository: ImagesRemoteRepository
) : ImageRepository {

    override fun getImages(): Observable<List<ImageEntity>> {
        return imagesRemoteRepository.getImages()
    }
}