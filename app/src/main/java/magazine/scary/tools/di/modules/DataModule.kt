package magazine.scary.tools.di.modules

import dagger.Module
import dagger.Provides
import magazine.scary.data.api.Api
import magazine.scary.data.repositories.movie.MoviesRemoteRepository
import magazine.scary.data.repositories.movie.MoviesRepositoryImpl
import magazine.scary.data.repositories.story.StoriesRemoteRepository
import magazine.scary.data.repositories.story.StoriesRepositoryImpl
import magazine.scary.domain.MovieRepository
import magazine.scary.domain.StoryRepository
import magazine.scary.domain.use_cases.GetMovies
import magazine.scary.domain.use_cases.GetPosters
import magazine.scary.domain.use_cases.GetStories
import magazine.scary.domain.use_cases.GetStory
import magazine.scary.tools.utils.AsyncTransformer
import javax.inject.Singleton

@Module
open class DataModule {

    @Singleton
    @Provides
    fun provideMovieRepo(api: Api): MovieRepository {
        return MoviesRepositoryImpl(
            MoviesRemoteRepository(
                api
            )
        )
    }
    @Singleton
    @Provides
    fun provideStoryRepo(api: Api): StoryRepository {
        return StoriesRepositoryImpl(
            StoriesRemoteRepository(
                api
            )
        )
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

    @Singleton
    @Provides
    fun getStories(storyRepository: StoryRepository): GetStories {
        return GetStories(storyRepository = storyRepository, transformer = AsyncTransformer())
    }

    @Singleton
    @Provides
    fun getStory(storyRepository: StoryRepository): GetStory {
        return GetStory(storyRepository = storyRepository, transformer = AsyncTransformer())
    }


}