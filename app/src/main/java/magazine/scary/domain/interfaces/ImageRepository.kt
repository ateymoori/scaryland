package magazine.scary.domain.interfaces

import io.reactivex.Observable
import magazine.scary.domain.entities.ImageEntity

interface ImageRepository {
    fun getImages(): Observable<List<ImageEntity>>
}
