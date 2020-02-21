package magazine.scary.repository.rest

import magazine.scary.domain.entities.ResponseModel
import retrofit2.http.*

interface RestService {

//    @GET("ShortStories")
//    suspend fun searchImage(): ShortStoryModel

    @GET("https://pixabay.com/api/?key=14649220-5ae78e4612f86b869152790a4&image_type=photo&per_page=200&orientation=vertical")
    suspend fun getImages(@Query("q") word: String): ResponseModel

}