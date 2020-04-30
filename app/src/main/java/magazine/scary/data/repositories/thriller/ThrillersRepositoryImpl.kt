package magazine.scary.data.repositories.thriller

import io.reactivex.Observable
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.domain.interfaces.ThrillerRepository

class ThrillersRepositoryImpl
constructor(
    private val thrillersRemoteRepository: ThrillersRemoteRepository
) : ThrillerRepository {

    override fun getThrillers(movieId: Int): Observable<List<ThrillerEntity>> {
        return thrillersRemoteRepository.getThrillersById(movieId)
    }
}