package magazine.scary.data.repositories.movie

import io.reactivex.Observable
import magazine.scary.domain.interfaces.MovieRepository
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity

class MoviesRepositoryImpl
constructor(
    private val moviesRemoteRepository: MoviesRemoteRepository
) : MovieRepository {

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesRemoteRepository.getMovies()
    }

    override fun getPosters(movieId: Int): Observable<List<PosterEntity>> {
        return moviesRemoteRepository.getPostersById(movieId)
    }
}