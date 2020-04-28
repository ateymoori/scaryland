package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.MovieRepository
import magazine.scary.domain.common.Transformer
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity


class GetPosters(
    transformer: Transformer<List<PosterEntity>>,
    private val moviesRepository: MovieRepository
) : UseCase<List<PosterEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<PosterEntity>> {
        val movieID = data?.get("movieID") as? Int
        movieID?.let {
            return moviesRepository.getPosters(movieID)

        } ?: return Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }
}