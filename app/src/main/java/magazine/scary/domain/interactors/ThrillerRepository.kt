package magazine.scary.domain.interactors

import io.reactivex.Observable
import magazine.scary.domain.entities.ThrillerEntity

interface ThrillerRepository {
    fun getThrillers(movieID: Int): Observable<List<ThrillerEntity>>
}
