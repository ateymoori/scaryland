package magazine.scary.presentation.ui.image_detail


import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        imageLoader.load(
            preLoadUrl = item.previewURL,
            url = item.largeImageURL,
            imageView = image
        )
        setWallpaper.setOnClickListener { setWallpaper() }
    }

    private fun setWallpaper(){
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val wallpaperManager = WallpaperManager.getInstance(activity)
        wallpaperManager.setBitmap(bitmap)
    }

}
