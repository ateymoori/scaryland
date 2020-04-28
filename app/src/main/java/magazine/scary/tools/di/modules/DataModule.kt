package magazine.scary.tools.di.modules

import dagger.Module
import dagger.Provides
import magazine.scary.data.api.Api
import magazine.scary.data.repositories.MoviesRemoteRepository
import magazine.scary.data.repositories.MoviesRepositoryImpl
import magazine.scary.domain.MovieRepository
import magazine.scary.domain.use_cases.GetMovies
import magazine.scary.domain.use_cases.GetPosters
import magazine.scary.tools.utils.AsyncTransformer
import javax.inject.Singleton

@Module
open class DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(api: Api): MovieRepository {
        return MoviesRepositoryImpl(MoviesRemoteRepository(api))
    }

    @Singleton
    @Provides
    fun movieDetails(movieRepository: MovieRepository): GetMovies {
        return GetMovies(moviesRepository = movieRepository, transformer = AsyncTransformer())
    }

    @Singleton
    @Provides
    fun getPosters(movieRepository: MovieRepository): GetPosters {
        return GetPosters(moviesRepository = movieRepository, transformer = AsyncTransformer())
    }


}