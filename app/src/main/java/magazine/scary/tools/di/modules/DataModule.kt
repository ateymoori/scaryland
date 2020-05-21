package magazine.scary.tools.di.modules

import dagger.Module
import dagger.Provides
import magazine.scary.data.api.Api
import magazine.scary.data.repositories.image.ImageRepositoryImpl
import magazine.scary.data.repositories.image.ImagesRemoteRepository
import magazine.scary.data.repositories.movie.MoviesRemoteRepository
import magazine.scary.data.repositories.movie.MoviesRepositoryImpl
import magazine.scary.data.repositories.story.StoriesRemoteRepository
import magazine.scary.data.repositories.story.StoriesRepositoryImpl
import magazine.scary.data.repositories.thriller.ThrillersRemoteRepository
import magazine.scary.data.repositories.thriller.ThrillersRepositoryImpl
import magazine.scary.data.repositories.translate.TranslateRemoteRepository
import magazine.scary.data.repositories.translate.TranslateRepositoryImpl
import magazine.scary.domain.interactors.*
import magazine.scary.domain.use_cases.*
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
    fun provideThrillerRepo(api: Api): ThrillerRepository {
        return ThrillersRepositoryImpl(
            ThrillersRemoteRepository(
                api
            )
        )
    }

    @Singleton
    @Provides
    fun provideImageRepo(api: Api): ImageRepository {
        return ImageRepositoryImpl(
            ImagesRemoteRepository(
                api
            )
        )
    }
    @Singleton
    @Provides
    fun provideTranslateRepo(api: Api): TranslateRepository {
        return TranslateRepositoryImpl(
            TranslateRemoteRepository(
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

    @Singleton
    @Provides
    fun getThrillers(thrillerRepository: ThrillerRepository): GetThrillers {
        return GetThrillers(
            thrillerRepository = thrillerRepository,
            transformer = AsyncTransformer()
        )
    }

    @Singleton
    @Provides
    fun getImages(imageRepository: ImageRepository): GetImages {
        return GetImages(
            imageRepository = imageRepository,
            transformer = AsyncTransformer()
        )
    }

    @Singleton
    @Provides
    fun getTranslate(translateRepository: TranslateRepository ): Translate {
        return Translate(
            translateRepository = translateRepository,
            transformer = AsyncTransformer()
        )
    }



}