package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.common.Transformer
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.domain.interfaces.ThrillerRepository


class GetThrillers(
    transformer: Transformer<List<ThrillerEntity>>,
    private val thrillerRepository: ThrillerRepository
) : UseCase<List<ThrillerEntity>>(transformer) {


    companion object {
        private const val PARAM_MOVIE_ID = "movieID"
    }

    fun getThrillers(movieID: Int): Observable<List<ThrillerEntity>> {
        val data = HashMap<String, Int>()
        data[PARAM_MOVIE_ID] = movieID
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<ThrillerEntity>> {
        val movieID = data?.get(PARAM_MOVIE_ID) as? Int
        movieID?.let {
            return thrillerRepository.getThrillers(it)

        } ?: return Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }
}

