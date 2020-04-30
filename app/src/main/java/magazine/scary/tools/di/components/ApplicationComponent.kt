package magazine.scary.tools.di.components

import android.app.Application
import magazine.scary.tools.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.DaggerApplication
import magazine.scary.tools.di.modules.*
import magazine.scary.tools.utils.MyApplication

@Singleton
@Component(
    modules = [
        ContextModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class,
        DataModule::class,
        RoomModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}