package magazine.scary.data.api

import io.reactivex.Observable
import magazine.scary.data.entities.*
import magazine.scary.domain.entities.*
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {
//    @FormUrlEncoded
//    @Streaming
//    @POST("https://translate.googleapis.com/translate_a/single")
//      fun translate(
//        @Field("client") client: String,
//        @Field("sl") sl: String,
//        @Field("dt") dt: String,
//        @Field("tl") lang_code: String,
//        @Field("q") word: String
//    ): Observable<ResponseBody>

    @GET("movies/posters/{movieID}")
    fun getMoviePosters(@Path("movieID") movieID: Int): Observable<List<PosterData>>

    @GET("movies")
    fun getMovies(): Observable<List<MovieData>>

    @GET("stories/")
    fun getStories(): Observable<List<StoryData>>

    @GET("stories/{id}")
    fun getStory(@Path("id") id: Int): Observable<StoryData>

    @GET("movies/thrillers/{id}")
    fun getThrillers(@Path("id") id: Int): Observable<List<ThrillerData>>

    @GET("images")
    fun getImages(): Observable<List<ImageData>>


    @FormUrlEncoded
    @POST("translate")
    fun translate(
        @Field("language_code") language_code: String,
        @Field("word") word: String
    ): Observable<TranslateData>


}