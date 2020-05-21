package magazine.scary.domain.interactors

import io.reactivex.Observable
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity

interface MovieRepository {
    fun getMovies(): Observable<List<MovieEntity>>
    fun getPosters(movieId: Int): Observable<List<PosterEntity>>
}
