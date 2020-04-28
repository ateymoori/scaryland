package magazine.scary.data.api

import io.reactivex.Observable
import magazine.scary.data.entities.MovieData
import magazine.scary.data.entities.PosterData
import magazine.scary.domain.entities.*
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {

    @GET("https://pixabay.com/api/?key=14649220-5ae78e4612f86b869152790a4&image_type=photo&per_page=200&orientation=vertical")
    suspend fun getImages(@Query("q") word: String): ResponseModel

//    @GET("https://api.backendless.com/C1A6E4E6-FAE9-9C53-FFC4-070083CDDB00/00B121F3-BA9F-4A3E-8B31-46033E7141EC/data/movies?pageSize=99")
//    suspend fun getMovies(): List<MovieModel>

//    @GET("https://api.backendless.com/C1A6E4E6-FAE9-9C53-FFC4-070083CDDB00/00B121F3-BA9F-4A3E-8B31-46033E7141EC/data/stories?sortBy=created%20desc&pageSize=99")
//    suspend fun getStories(): List<StoryModel>
//
//    @Streaming
//    @GET("{fileAddress}")
//    suspend fun getStory(@Path("fileAddress", encoded = true) objectID: String): ResponseBody

    @FormUrlEncoded
    @Streaming
    @POST("https://translate.googleapis.com/translate_a/single")
    suspend fun translate(
        @Field("client") client: String,
        @Field("sl") sl: String,
        @Field("dt") dt: String,
        @Field("tl") lang_code: String,
        @Field("q") word: String
    ): ResponseBody



    @GET("http://amirteymoori.ir/voyager/public/api/stories/")
    suspend fun getStories(): List<StoryModel>

    @GET("http://amirteymoori.ir/voyager/public/api/stories/{id}")
    suspend fun getStory(@Path("id") id: String): StoryModel


    @GET("http://amirteymoori.ir/voyager/public/api/movies/thrillers/{id}")
    suspend fun getThrillers(@Path("id") id: String): List<ThrillerModel>



    //clean architecture

    @GET("http://amirteymoori.ir/voyager/public/api/movies/posters/{movieID}")
    fun getMoviePosters(@Path("movieID") movieID: Int): Observable<List<PosterData>>

    @GET("http://amirteymoori.ir/voyager/public/api/movies")
    fun getMovies(): Observable<List<MovieData>>
}