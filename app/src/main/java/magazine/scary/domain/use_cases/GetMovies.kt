package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.MovieRepository
import magazine.scary.domain.common.Transformer
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity


class GetMovies(transformer: Transformer<List<MovieEntity>>,
                private val moviesRepository: MovieRepository) : UseCase<List<MovieEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesRepository.getMovies()
    }
}