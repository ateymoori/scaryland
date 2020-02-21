package magazine.scary.tools.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import magazine.scary.presentation.ui.container.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

}