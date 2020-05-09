package magazine.scary.domain.use_cases

import io.reactivex.Observable
import magazine.scary.domain.common.Transformer
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.TranslateEntity
import magazine.scary.domain.interfaces.TranslateRepository


class Translate(
    transformer: Transformer<TranslateEntity>,
    private val translateRepository: TranslateRepository
) : UseCase<TranslateEntity>(transformer) {


    companion object {
        private const val PARAM_LANGUAGE_CODE = "language_code"
        private const val PARAM_WORD = "word"
    }

    fun translate(language_code: String, word: String): Observable<TranslateEntity> {
        val data = HashMap<String, String>()
        data[PARAM_LANGUAGE_CODE] = language_code
        data[PARAM_WORD] = word
        return observable(data)
    }


    override fun createObservable(data: Map<String, Any>?): Observable<TranslateEntity> {
        val languageCode = data?.get(PARAM_LANGUAGE_CODE) as? String
        val word = data?.get(PARAM_WORD) as? String
        if (languageCode == null || word == null)
            return Observable.error {
                IllegalArgumentException("StoryID must be provided.")
            }
        return translateRepository.translate(languageCode, word)

    }
}