package magazine.scary.domain

import io.reactivex.Observable
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.StoryEntity

interface StoryRepository {
    fun getStories(): Observable<List<StoryEntity>>
    fun getStory(storyID: Int): Observable<StoryEntity>
}
