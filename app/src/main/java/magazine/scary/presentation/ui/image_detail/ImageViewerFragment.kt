package magazine.scary.presentation.ui.image_detail


import android.Manifest
import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.pixabay.utils.tools.BitmapToGalleryUtil.Companion.saveImage
import com.pixabay.utils.tools.toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_image_viewer.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageModel
import magazine.scary.tools.utils.Cons.Companion.ITEM_BUNDLE
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject


class ImageViewerFragment : Fragment() {
    @Inject
    lateinit var imageLoader: ImageLoader

    lateinit var item: ImageModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_viewer, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ITEM_BUNDLE)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        back.setOnClickListener { activity?.onBackPressed() }
        setWallpaper.setOnClickListener { setWallpaper() }
        download.setOnClickListener { saveToGallery() }

        imageLoader.load(
            preLoadUrl = item.previewURL,
            url = item.largeImageURL,
            imageView = image,
            loadingView = loading
        )
        imageLoader.load(url = item.userImageURL, imageView = avatar)

        username.text = item.user
        viewsCount.setCount(item.views)
        favoritesCount.setCount(item.favorites)

        if (item.favorites == null)
            favoritesCount.visibility = View.GONE

        if (item.views == null)
            viewsCount.visibility = View.GONE

        if (item.user == null)
            username.visibility = View.GONE

        if (item.userImageURL == null)
            avatar.visibility = View.GONE


    }

    private fun setWallpaper() {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val wallpaperManager = WallpaperManager.getInstance(activity)
        wallpaperManager.setBitmap(bitmap)
        "Your wallpaper has been changed".toast(activity)
    }

    private fun saveToGallery() {
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    val bitmap = (image.drawable as BitmapDrawable).bitmap
                    saveImage(bitmap, activity as Context, "horror_magazine")
                    "Image added to your Gallery".toast(activity)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    "Please give Storage permission at Setting".toast(activity)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    "Please give Storage permission at Setting".toast(activity)
                }
            }).check()
    }


}
