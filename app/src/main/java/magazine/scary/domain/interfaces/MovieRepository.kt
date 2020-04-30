package magazine.scary.domain.interfaces

import io.reactivex.Observable
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity

interface MovieRepository {
    fun getMovies(): Observable<List<MovieEntity>>
    fun getPosters(movieId: Int): Observable<List<PosterEntity>>
}
