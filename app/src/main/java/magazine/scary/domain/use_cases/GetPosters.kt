package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.interfaces.MovieRepository
import magazine.scary.domain.common.Transformer
import magazine.scary.domain.entities.PosterEntity


class GetPosters(
    transformer: Transformer<List<PosterEntity>>,
    private val moviesRepository: MovieRepository
) : UseCase<List<PosterEntity>>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ID = "movieID"
    }

    fun getPosters(movieID: Int): Observable<List<PosterEntity>> {
        val data = HashMap<String, Int>()
        data[PARAM_MOVIE_ID] = movieID
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<PosterEntity>> {
        val movieID = data?.get(PARAM_MOVIE_ID) as? Int
        movieID?.let {
            return moviesRepository.getPosters(it)

        } ?: return Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }


}