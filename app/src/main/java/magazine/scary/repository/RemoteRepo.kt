package magazine.scary.repository

import magazine.scary.domain.entities.MovieModel
import magazine.scary.domain.entities.ThrillerModel
import magazine.scary.repository.rest.RestService
import javax.inject.Inject

class RemoteRepo @Inject
constructor(private val repoService: RestService) {

    suspend fun searchImages(word: String) =
        repoService.getImages(
            word
        )

    suspend fun getMovies(): List<MovieModel> {
        return repoService.getMovies()
    }

    suspend fun getThrillers(id: String): List<ThrillerModel> {
        return repoService.getThrillers(id)
    }

    suspend fun getStories() =
        repoService.getStories()

    suspend fun getStory(id: String) =
        repoService.getStory(id)

    suspend fun getMoviePosters(movieID: String) =
        repoService.getMoviePosters(movieID)

    suspend fun translate(language_code: String, word: String) =
        repoService.translate(
            lang_code = language_code,
            word = word,
            client = "gtx",
            sl = "auto",
            dt = "t"
        )
}