package magazine.scary.domain.interfaces

import io.reactivex.Observable
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.entities.TranslateEntity

interface TranslateRepository {
    fun translate(language_code: String, word: String ): Observable<TranslateEntity>
}