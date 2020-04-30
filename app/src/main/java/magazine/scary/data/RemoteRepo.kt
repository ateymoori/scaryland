package magazine.scary.data

import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.data.api.Api
import javax.inject.Inject

class RemoteRepo @Inject
constructor(private val repoService: Api) {


    suspend fun translate(language_code: String, word: String) =
        repoService.translate(
            lang_code = language_code,
            word = word,
            client = "gtx",
            sl = "auto",
            dt = "t"
        )
}