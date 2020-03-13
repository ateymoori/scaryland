package magazine.scary.repository

import magazine.scary.repository.rest.RestService
import javax.inject.Inject

class RemoteRepo @Inject
constructor(private val repoService: RestService) {

    suspend fun searchImages(word: String) =
        repoService.getImages(
            word
        )

    suspend fun getMovies() =
        repoService.getMovies()

    suspend fun getStories() =
        repoService.getStories()

    suspend fun getStory(fileAddress: String) =
        repoService.getStory(fileAddress)

    suspend fun translate(language_code: String, word: String) =
        repoService.translate(lang_code = language_code, word = word , client = "gtx" , sl ="auto" , dt="t" )
}