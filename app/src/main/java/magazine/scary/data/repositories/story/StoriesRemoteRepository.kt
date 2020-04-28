package magazine.scary.data.repositories.story

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.domain.MovieDataStore
import magazine.scary.data.mappers.MovieDataEntityMapper
import magazine.scary.data.mappers.PosterDataEntityMapper
import magazine.scary.data.mappers.StoryDataEntityMapper
import magazine.scary.domain.StoryDataStore
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.Optional
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.StoryEntity

class StoriesRemoteRepository
constructor(
    private val api: Api
) : StoryDataStore {

    private val storyDataMapper = StoryDataEntityMapper()

    override fun getStoryById(storyID: Int): Observable<StoryEntity> {
        return api.getStory(storyID).flatMap { detailedData ->
            Observable.just(storyDataMapper.mapFrom(detailedData))
        }
    }

    override fun getStories(): Observable<List<StoryEntity>> {
        return api.getStories().map { results ->
            results.map { storyDataMapper.mapFrom(it) }
        }
    }
}