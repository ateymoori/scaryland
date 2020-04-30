package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.common.Transformer
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.interfaces.ImageRepository


class GetImages(
    transformer: Transformer<List<ImageEntity>>,
    private val imageRepository: ImageRepository
) : UseCase<List<ImageEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<ImageEntity>> {
        return imageRepository.getImages()

    }
}

