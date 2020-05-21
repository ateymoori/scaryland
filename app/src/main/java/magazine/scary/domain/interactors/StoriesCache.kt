package magazine.scary.domain.interactors

import io.reactivex.Observable
import magazine.scary.domain.common.Optional
import magazine.scary.domain.entities.StoryEntity

interface StoriesCache {
    fun clear()
    fun save(storyEntity: StoryEntity)
    fun remove(storyEntity: StoryEntity)
    fun saveAll(storyEntities: List<StoryEntity>)
    fun getAll(): Observable<List<StoryEntity>>
    fun get(storyId: Int): Observable<Optional<StoryEntity>>
    fun search(query: String): Observable<List<StoryEntity>>
    fun isEmpty(): Observable<Boolean>
}