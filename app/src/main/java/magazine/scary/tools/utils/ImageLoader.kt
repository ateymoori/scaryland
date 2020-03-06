package magazine.scary.tools.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pixabay.utils.tools.toast
import javax.inject.Inject

class
ImageLoader @Inject constructor(
    private val context: Context
) {

    fun load(
        preLoadUrl: String? = null,
        url: String?,
        imageView: ImageView?,
        loadingView: View? = null
    ) {
        if (url != null && imageView != null) {
            Glide.with(context).load(
                    url
                ).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadingView?.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadingView?.visibility=View.GONE
                        return false
                    }

                })

                .thumbnail(
                    Glide.with(context)
                        .load(preLoadUrl)
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }

    }




}