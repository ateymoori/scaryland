package magazine.scary.tools.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import magazine.scary.presentation.ui.dashboard.DashboardFragment
import magazine.scary.presentation.ui.splash.SplashScreenFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeSplashFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDashboardFragment(): DashboardFragment

}