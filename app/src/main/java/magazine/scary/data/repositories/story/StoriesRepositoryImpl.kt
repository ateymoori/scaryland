package magazine.scary.data.repositories.story

import io.reactivex.Observable
import magazine.scary.data.repositories.movie.MoviesRemoteRepository
import magazine.scary.domain.MovieRepository
import magazine.scary.domain.StoryRepository
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
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