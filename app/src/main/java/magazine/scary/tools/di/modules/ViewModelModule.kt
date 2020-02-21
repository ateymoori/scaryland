package magazine.scary.tools.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import com.pixabay.utils.di.ViewModelFactory
import com.pixabay.utils.di.ViewModelKey
import dagger.Module
import dagger.multibindings.IntoMap
import magazine.scary.presentation.ui.dashboard.DashboardViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun dashboardVM(viewModel: DashboardViewModel): ViewModel

}