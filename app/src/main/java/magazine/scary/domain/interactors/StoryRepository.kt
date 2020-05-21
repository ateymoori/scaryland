package magazine.scary.domain.interactors

import io.reactivex.Observable
import magazine.scary.domain.entities.StoryEntity

interface StoryRepository {
    fun getStories(): Observable<List<StoryEntity>>
    fun getStory(storyID: Int): Observable<StoryEntity>
}
