package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.common.Transformer
import magazine.scary.domain.interactors.StoryRepository
import magazine.scary.domain.entities.StoryEntity


class GetStory(
    transformer: Transformer<StoryEntity>,
    private val storyRepository: StoryRepository
) : UseCase<StoryEntity>(transformer) {


    companion object {
        private const val PARAM_STORY_ID = "storyID"
    }

    fun getStory(storyID: Int): Observable<StoryEntity> {
        val data = HashMap<String, Int>()
        data[PARAM_STORY_ID] = storyID
        return observable(data)
    }


    override fun createObservable(data: Map<String, Any>?): Observable<StoryEntity> {
        val storyID = data?.get(PARAM_STORY_ID) as? Int
        storyID?.let {
            return storyRepository.getStory(it)

        } ?: return Observable.error { IllegalArgumentException("StoryID must be provided.") }
    }
}
