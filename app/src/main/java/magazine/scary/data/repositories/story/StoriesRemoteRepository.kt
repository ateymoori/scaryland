package magazine.scary.data.repositories.story

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.data.mappers.StoryDataEntityMapper
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.interactors.StoryRepository

class StoriesRemoteRepository
constructor(
    private val api: Api
) : StoryRepository {

    private val storyDataMapper = StoryDataEntityMapper()



    override fun getStories(): Observable<List<StoryEntity>> {
        return api.getStories().map { results ->
            results.map { storyDataMapper.mapFrom(it) }
        }
    }

    override fun getStory(storyID: Int): Observable<StoryEntity> {
        return api.getStory(storyID).flatMap { detailedData ->
            Observable.just(storyDataMapper.mapFrom(detailedData))
        }
    }
}