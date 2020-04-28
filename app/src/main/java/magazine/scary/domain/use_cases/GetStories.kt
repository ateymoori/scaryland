package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.MovieRepository
import magazine.scary.domain.common.Transformer
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.StoryRepository
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity


class GetStories(transformer: Transformer<List<StoryEntity>>,
                 private val storyRepository: StoryRepository) : UseCase<List<StoryEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<StoryEntity>> {
        return storyRepository.getStories()
    }
}