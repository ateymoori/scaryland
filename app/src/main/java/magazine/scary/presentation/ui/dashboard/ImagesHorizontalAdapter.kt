package magazine.scary.presentation.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.tools.listen
import kotlinx.android.synthetic.main.image_portrait_item.view.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class ImagesHorizontalAdapter @Inject constructor() :
    RecyclerView.Adapter<ImagesHorizontalAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var imageClickListener: ImageClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_portrait_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            images?.get(pos)?.let { imageClickListener.onImageClicked(it) }
        }
    }

    var images:List<ImageEntity>? = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ImageClickListener {
        fun onImageClicked(image: ImageEntity)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = images?.get(position)
        imageLoader.load(
            preLoadUrl = item?.previewURL,
            url = item?.largeImageURL,
            imageView = holder.image
        )


    }

    override fun getItemCount(): Int {
        return images?.size ?:0
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
          val image: ImageView = view.image
    }

}