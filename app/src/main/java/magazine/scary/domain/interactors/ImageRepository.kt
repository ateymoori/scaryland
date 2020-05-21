package magazine.scary.domain.interactors

import io.reactivex.Observable
import magazine.scary.domain.entities.ImageEntity

interface ImageRepository {
    fun getImages(): Observable<List<ImageEntity>>
}
