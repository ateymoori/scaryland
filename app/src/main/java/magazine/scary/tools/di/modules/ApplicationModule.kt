package magazine.scary.tools.di.modules

import android.util.Log
import com.pixabay.utils.di.RetrofitServiceGenerator
import dagger.Module
import dagger.Provides
import magazine.scary.repository.rest.RestService
import magazine.scary.tools.utils.Cons
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: GsonConverterFactory,
        textConverter: ScalarsConverterFactory,
        httpClient: OkHttpClient.Builder,
        @Named("baseURL") baseURL: String
    ): Retrofit {
        val retrofitClass =
            RetrofitServiceGenerator(
                textConverter,
                converter,
                httpClient,
                baseURL
            )
        return retrofitClass.getClient()
    }

    @Singleton
    @Provides
    fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient().newBuilder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.callTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor)
        return httpClient
    }

    @Singleton
    @Provides
    fun provedHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                //   Logger.d(message)
              Log.d("okhttp", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGSONConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Singleton
    @Provides
    fun provideTextConverterFactory(): ScalarsConverterFactory {
        return ScalarsConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("baseURL")
    fun provideBaseURL(): String {
        return Cons.BASE_URL
    }

    @Singleton
    @Provides
    fun getMainInterface(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideVideoDetailsFragment(  ): VideoDetailsFragment {
//        return VideoDetailsFragment()
//    }
//    @Singleton
//    @Provides
//    fun provideFragmentPagerAdapter( videoDetailsFragment: VideoDetailsFragment): FragmentManager {
//        return videoDetailsFragment.childFragmentManager
//    }
//    @Singleton
//    @Provides
//    fun provideMoviePagerAdapter( fm:FragmentManager): MoviePagerAdapter {
//        return MoviePagerAdapter(fm)
//    }

}