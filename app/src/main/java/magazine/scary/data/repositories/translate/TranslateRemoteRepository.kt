package magazine.scary.data.repositories.translate

import io.reactivex.Observable
import magazine.scary.data.api.Api
import magazine.scary.data.mappers.TranslateDataEntityMapper
import magazine.scary.domain.entities.TranslateEntity
import magazine.scary.domain.interactors.TranslateRepository

class TranslateRemoteRepository
constructor(
    private val api: Api
) : TranslateRepository {

    private val translateMapper = TranslateDataEntityMapper()

    override fun translate(language_code: String, word: String): Observable<TranslateEntity> {
        return api.translate(language_code , word).flatMap { detailedData ->
            Observable.just(translateMapper.mapFrom(detailedData))
        }
    }


}