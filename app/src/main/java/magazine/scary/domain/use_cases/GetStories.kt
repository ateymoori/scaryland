package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.common.Transformer
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.entities.StoryEntity


class GetStories(transformer: Transformer<List<StoryEntity>>,
                 private val storyRepository: StoryRepository
) : UseCase<List<StoryEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<StoryEntity>> {
        return storyRepository.getStories()
    }
}