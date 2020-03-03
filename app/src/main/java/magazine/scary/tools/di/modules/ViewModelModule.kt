package magazine.scary.tools.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import com.pixabay.utils.di.ViewModelFactory
import com.pixabay.utils.di.ViewModelKey
import dagger.Module
import dagger.multibindings.IntoMap
import magazine.scary.presentation.ui.dashboard.DashboardViewModel
import magazine.scary.presentation.ui.images_list.ImagesListViewModel
import magazine.scary.presentation.ui.movies_list.MoviesListViewModel
import magazine.scary.presentation.ui.stories_list.StoriesListViewModel
import magazine.scary.presentation.ui.story_detail.StoryDetailReaderViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun dashboardVM(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImagesListViewModel::class)
    internal abstract fun imagesListVM(viewModel: ImagesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    internal abstract fun moviesListVM(viewModel: MoviesListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(StoryDetailReaderViewModel::class)
    internal abstract fun storyReaderVM(viewModel: StoryDetailReaderViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(StoriesListViewModel::class)
    internal abstract fun storyListVM(viewModel: StoriesListViewModel): ViewModel

}