package magazine.scary.domain.interactors

import io.reactivex.Observable
import magazine.scary.domain.entities.TranslateEntity

interface TranslateRepository {
    fun translate(language_code: String, word: String ): Observable<TranslateEntity>
}