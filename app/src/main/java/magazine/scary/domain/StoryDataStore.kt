package magazine.scary.domain

import io.reactivex.Observable
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.StoryEntity

interface StoryDataStore {
    fun getStoryById(storyID: Int): Observable<StoryEntity>
    fun getStories(): Observable<List<StoryEntity>>
}