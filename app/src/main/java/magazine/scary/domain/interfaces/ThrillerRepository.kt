package magazine.scary.domain.interfaces

import io.reactivex.Observable
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.ThrillerEntity

interface ThrillerRepository {
    fun getThrillers(movieID: Int): Observable<List<ThrillerEntity>>
}
