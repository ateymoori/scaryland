package magazine.scary.data.api

import io.reactivex.Observable
import magazine.scary.data.entities.*
import magazine.scary.domain.entities.*
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {
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

    @GET("http://amirteymoori.ir/voyager/public/api/movies/posters/{movieID}")
    fun getMoviePosters(@Path("movieID") movieID: Int): Observable<List<PosterData>>

    @GET("http://amirteymoori.ir/voyager/public/api/movies")
    fun getMovies(): Observable<List<MovieData>>

    @GET("http://amirteymoori.ir/voyager/public/api/stories/")
    fun getStories(): Observable<List<StoryData>>

    @GET("http://amirteymoori.ir/voyager/public/api/stories/{id}")
    fun getStory(@Path("id") id: Int): Observable<StoryData>

    @GET("http://amirteymoori.ir/voyager/public/api/movies/thrillers/{id}")
    fun getThrillers(@Path("id") id: Int): Observable<List<ThrillerData>>

    @GET("http://amirteymoori.ir/voyager/public/api/images")
    fun getImages(): Observable<List<ImageData>>

}