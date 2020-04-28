package magazine.scary.domain

import io.reactivex.Observable
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity

interface MovieDataStore {
    fun getPostersById(movieId: Int): Observable<List<PosterEntity>>
    fun getMovies(): Observable<List<MovieEntity>>
}