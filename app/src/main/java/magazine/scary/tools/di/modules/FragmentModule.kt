package magazine.scary.tools.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import magazine.scary.presentation.ui.dashboard.DashboardFragment
import magazine.scary.presentation.ui.image_detail.ImageViewerFragment
import magazine.scary.presentation.ui.images_list.ImagesListFragment
import magazine.scary.presentation.ui.movie_detail.VideoDetailsFragment
import magazine.scary.presentation.ui.movies_list.MoviesListFragment
import magazine.scary.presentation.ui.splash.SplashScreenFragment
import magazine.scary.presentation.ui.stories_list.StoryListFragment
import magazine.scary.presentation.ui.story_detail.StoryReaderFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeSplashFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeImagesListFragment(): ImagesListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeImagesViewerFragment(): ImageViewerFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMoviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeStoryReaderFragment(): StoryReaderFragment

    @ContributesAndroidInjector
    internal abstract fun contributeVideoDetailFragment(): VideoDetailsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeStoriesListFragment(): StoryListFragment

}