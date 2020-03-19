package magazine.scary.presentation.ui.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import magazine.scary.R
import magazine.scary.domain.entities.MenuModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuList.adapter = DrawerMenuAdapter(this , listOf(
            MenuModel(title = "Rate Us" , icon = R.drawable.ic_review) ,
            MenuModel(title = "Send Content" , icon = R.drawable.ic_review) ,
            MenuModel(title = "Share App" , icon = R.drawable.ic_review) ,
            MenuModel(title = "Send Feedbacck" , icon = R.drawable.ic_review)
        ))
    }

    fun toggleMenu(){
        if(!drawer.isDrawerOpen(GravityCompat.END))
            drawer.openDrawer(GravityCompat.END)
    }
}
