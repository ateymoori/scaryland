package magazine.scary.data.repositories.translate

import io.reactivex.Observable
import magazine.scary.domain.entities.TranslateEntity
import magazine.scary.domain.interactors.TranslateRepository

class TranslateRepositoryImpl
constructor(
    private val thrillersRemoteRepository: TranslateRemoteRepository
) : TranslateRepository {

    override fun translate(language_code: String, word: String): Observable<TranslateEntity> {
        return thrillersRemoteRepository.translate(language_code, word)
    }
}