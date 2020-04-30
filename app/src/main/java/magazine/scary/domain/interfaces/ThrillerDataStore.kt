package magazine.scary.domain.interfaces

import io.reactivex.Observable
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.ThrillerEntity

interface ThrillerDataStore {
    fun getThrillersById(movieId: Int): Observable<List<ThrillerEntity>>
}