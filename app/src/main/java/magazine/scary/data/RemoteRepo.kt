package magazine.scary.data

import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.ThrillerModel
import magazine.scary.data.api.Api
import javax.inject.Inject

class RemoteRepo @Inject
constructor(private val repoService: Api) {

    suspend fun searchImages(word: String) =
        repoService.getImages(
            word
        )

//    suspend fun getMovies(): List<MovieData> {
//        return repoService.getMovies()
//    }

    suspend fun getThrillers(id: String): List<ThrillerModel> {
        return repoService.getThrillers(id)
    }

    suspend fun getStories() =
        repoService.getStories()

//    suspend fun getStory(id: String) =
//        repoService.getStory(id)

//    suspend fun getMoviePosters(movieID: String) =
//        repoService.getMoviePosters(movieID)

    suspend fun translate(language_code: String, word: String) =
        repoService.translate(
            lang_code = language_code,
            word = word,
            client = "gtx",
            sl = "auto",
            dt = "t"
        )
}