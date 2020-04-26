package magazine.scary.presentation.ui.drawer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.pixabay.utils.tools.AppUtils
import com.pixabay.utils.tools.log
import com.pixabay.utils.tools.userModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*
import magazine.scary.R
import magazine.scary.domain.entities.MenuItemTypeEnum
import magazine.scary.domain.entities.MenuModel
import magazine.scary.presentation.ui.container.DrawerMenuAdapter
import magazine.scary.presentation.ui.container.MainActivity
import magazine.scary.tools.utils.ImageLoader
import magazine.scary.tools.utils.UserProvider
import javax.inject.Inject


class NavigationDrawerFragment : Fragment() {

    private val RC_SIGN_IN = 1000
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var userProvider: UserProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
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


        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this.activity as Activity, gso)
        sign_in_button.setOnClickListener { login() }
        logout.setOnClickListener { signOut() }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this.activity as Activity)
        updateUI(account)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this.activity as Activity, OnCompleteListener<Void?> {
              updateUI(null)
                userProvider.logOff()
            })
    }
    private fun updateUI(account: GoogleSignInAccount?) {
        with(account) {
            if (this != null){
                userProvider.setUser(account?.userModel())

                logout.visibility=View.VISIBLE
                avatar.visibility=View.VISIBLE
                name.visibility=View.VISIBLE
                sign_in_button.visibility=View.GONE
                imageLoader.load(
                    url = photoUrl.toString(),
                    imageView = avatar
                )
                name.text = "Hi $givenName"

            }else{
                logout.visibility=View.GONE
                sign_in_button.visibility=View.VISIBLE
                avatar.visibility=View.GONE
                name.visibility=View.INVISIBLE
            }
        }

    }

    private fun login() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

            updateUI(null)
        }
    }
}
