package magazine.scary.repository

import magazine.scary.domain.entities.ImageModel
import magazine.scary.domain.entities.MovieModel
import magazine.scary.domain.entities.StoryModel
import magazine.scary.repository.RemoteRepo
import okhttp3.ResponseBody
import javax.inject.Inject

class MainRepo @Inject
constructor(private val remoteRepo: RemoteRepo) {

    //if remote repo get error in rest connection, then Database will check to return cached data
    suspend fun getImages(word: String): List<ImageModel> {
        return remoteRepo.searchImages(word).hits
//        return try {
//            val remoteResults = remoteRepo.searchImages(word).hits
//            cacheRemoteResults(word, remoteResults)
//            remoteResults
//        } catch (e: UnknownHostException) {
//            dbRepo.search(word)
//        } catch (e: Exception) {
//            listOf()
//        }
    }

    suspend fun getMovies(): List<MovieModel> {
        return remoteRepo.getMovies()
    }

    suspend fun getStories(): List<StoryModel> {
        return remoteRepo.getStories()
    }

    suspend fun getStory(fileAddress: String) =
        remoteRepo.getStory(fileAddress)


    suspend fun translate(language_code: String, word: String) =
        remoteRepo.translate(language_code, word)

    //Save API data to DB, Send results to View Layer
//    private fun cacheRemoteResults(word: String, images: List<ImageModel>) {
//        dbRepo.insertAll(images.onEach { it.searchWord = word })
//    }

}