package magazine.scary.data.repositories.story

import io.reactivex.Observable
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.entities.StoryEntity

class StoriesRepositoryImpl
constructor(
    private val storiesRemoteRepository: StoriesRemoteRepository
) : StoryRepository {

    override fun getStories(): Observable<List<StoryEntity>> {
        return storiesRemoteRepository.getStories()
    }

    override fun getStory(storyID: Int): Observable<StoryEntity> {
        return storiesRemoteRepository.getStoryById(storyID)
    }
}