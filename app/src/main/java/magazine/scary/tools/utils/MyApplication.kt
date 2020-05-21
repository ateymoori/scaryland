package magazine.scary.tools.utils

import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import magazine.scary.tools.di.components.DaggerApplicationComponent

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)
        return component
    }

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
     }

}