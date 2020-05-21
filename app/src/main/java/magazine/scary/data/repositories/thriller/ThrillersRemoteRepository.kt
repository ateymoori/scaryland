package magazine.scary.data.repositories.thriller

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.data.mappers.ThrillerDataEntityMapper
import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.domain.interactors.ThrillerRepository

class ThrillersRemoteRepository
constructor(
    private val api: Api
) : ThrillerRepository {

    private val thrillerMapper = ThrillerDataEntityMapper()

    override fun getThrillers(movieID: Int): Observable<List<ThrillerEntity>> {
        return api.getThrillers(movieID).map { results ->
            results.map { thrillerMapper.mapFrom(it) }
        }
    }


}