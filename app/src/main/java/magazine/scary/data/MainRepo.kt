package magazine.scary.data

import magazine.scary.domain.entities.ImageEntity
import javax.inject.Inject

class MainRepo @Inject
constructor(private val remoteRepo: RemoteRepo) {

    suspend fun translate(language_code: String, word: String) =
        remoteRepo.translate(language_code, word)

}