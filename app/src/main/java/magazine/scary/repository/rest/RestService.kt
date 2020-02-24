package magazine.scary.repository.rest

import magazine.scary.domain.entities.MovieModel
import magazine.scary.domain.entities.ResponseModel
import magazine.scary.domain.entities.StoryModel
import retrofit2.http.*

interface RestService {

//    @GET("ShortStories")
//    suspend fun searchImage(): ShortStoryModel

    @GET("https://pixabay.com/api/?key=14649220-5ae78e4612f86b869152790a4&image_type=photo&per_page=200&orientation=vertical")
    suspend fun getImages(@Query("q") word: String): ResponseModel

    @GET("https://api.backendless.com/C1A6E4E6-FAE9-9C53-FFC4-070083CDDB00/00B121F3-BA9F-4A3E-8B31-46033E7141EC/data/movies")
    suspend fun getMovies(): List<MovieModel>

    @GET("https://api.backendless.com/C1A6E4E6-FAE9-9C53-FFC4-070083CDDB00/00B121F3-BA9F-4A3E-8B31-46033E7141EC/data/stories?props=image%2CobjectId%2Ctitle")
    suspend fun getStories(): List<StoryModel>


    @GET("https://api.backendless.com/C1A6E4E6-FAE9-9C53-FFC4-070083CDDB00/00B121F3-BA9F-4A3E-8B31-46033E7141EC/data/stories/{objectID}")
    suspend fun getStory(@Path("objectID") objectID: String): StoryModel

}