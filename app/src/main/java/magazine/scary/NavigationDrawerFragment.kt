package magazine.scary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixabay.utils.tools.AppUtils
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*
import magazine.scary.domain.entities.MenuItemTypeEnum
import magazine.scary.domain.entities.MenuModel
import magazine.scary.presentation.ui.container.DrawerMenuAdapter
import magazine.scary.presentation.ui.container.MainActivity

class NavigationDrawerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuList.adapter = DrawerMenuAdapter(
            activity, listOf(
                MenuModel(
                    title = "Rate Us",
                    icon = R.drawable.ic_review,
                    type = MenuItemTypeEnum.RATE_APP
                ),
                MenuModel(
                    title = "Send Content",
                    icon = R.drawable.ic_survey,
                    type = MenuItemTypeEnum.SEND_CONTENT
                ),
                MenuModel(
                    title = "Share App",
                    icon = R.drawable.ic_share,
                    type = MenuItemTypeEnum.SHARE_APP
                ),
                MenuModel(
                    title = "Your Feedback",
                    icon = R.drawable.ic_feedback,
                    type = MenuItemTypeEnum.FEEDBACK
                )
            )
        )

        menuList.setOnItemClickListener { adapterView, _, position, _ ->
            when ((adapterView.getItemAtPosition(position) as? MenuModel)?.type) {
                MenuItemTypeEnum.RATE_APP -> AppUtils.openGooglePlay(activity)
                MenuItemTypeEnum.SEND_CONTENT -> {
                }
                MenuItemTypeEnum.SHARE_APP -> AppUtils.shareToMessagingApps(
                    activity, title = "Hi there!",
                    message = "Install Horror Magazine to get new Books, Stories, Movies and Image backgrounds in Horror Genre. \n https://play.google.com/store/apps/details?id=magazine.scary"
                )
                MenuItemTypeEnum.FEEDBACK -> {
                }
                null -> {
                }
            }
            (activity as? MainActivity)?.toggleMenu()
        }
    }
}
