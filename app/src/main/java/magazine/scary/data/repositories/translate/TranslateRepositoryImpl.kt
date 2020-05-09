package magazine.scary.data.repositories.translate

import io.reactivex.Observable
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.domain.entities.TranslateEntity
import magazine.scary.domain.interfaces.ThrillerRepository
import magazine.scary.domain.interfaces.TranslateRepository

class TranslateRepositoryImpl
constructor(
    private val thrillersRemoteRepository: TranslateRemoteRepository
) : TranslateRepository {

    override fun translate(language_code: String, word: String): Observable<TranslateEntity> {
        return thrillersRemoteRepository.translate(language_code, word)
    }
}