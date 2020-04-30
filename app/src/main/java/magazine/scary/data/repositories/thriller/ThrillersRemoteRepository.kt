package magazine.scary.data.repositories.thriller

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.data.mappers.ThrillerDataEntityMapper
import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.domain.interfaces.ThrillerDataStore

class ThrillersRemoteRepository
constructor(
    private val api: Api
) : ThrillerDataStore {

    private val thrillerMapper = ThrillerDataEntityMapper()

    override fun getThrillersById(movieId: Int): Observable<List<ThrillerEntity>> {
        return api.getThrillers(movieId).map { results ->
            results.map { thrillerMapper.mapFrom(it) }
        }
    }


}