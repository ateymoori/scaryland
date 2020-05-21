package magazine.scary.data.repositories.movie

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.data.mappers.MovieDataEntityMapper
import magazine.scary.data.mappers.PosterDataEntityMapper
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.interactors.MovieRepository

class MoviesRemoteRepository
constructor(
    private val api: Api
    ) : MovieRepository {

    private val movieDataMapper = MovieDataEntityMapper()
    private val posterDataMapper = PosterDataEntityMapper()

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getMovies().map { results ->
            results.map { movieDataMapper.mapFrom(it) }
        }
    }

    override fun getPosters(movieId: Int): Observable<List<PosterEntity>> {
        return api.getMoviePosters(movieId).map { results ->
            results.map { posterDataMapper.mapFrom(it) }
        }
    }
}