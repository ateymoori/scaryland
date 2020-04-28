package magazine.scary.presentation.ui.movie_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.tools.listen
import kotlinx.android.synthetic.main.image_portrait_item.view.*
import magazine.scary.R
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class PostersHorizontalAdapter @Inject constructor() :
    RecyclerView.Adapter<PostersHorizontalAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var imageClickListener: ImageClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): Holder {
        ctx = parent.context

        var layout = if( itemType==ItemType.PORTRAT.ordinal)  R.layout.image_portrait_item else R.layout.image_landscape_item
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                layout,
                parent,
                false
            )
        ).listen { pos, _ ->
            images?.get(pos)?.let { imageClickListener.onImageClicked(it) }
        }
    }

    enum class ItemType {
        PORTRAT, LANDSCAPE
    }

    override fun getItemViewType(position: Int): Int {
        return if ((images?.get(position)?.aspect_ratio ?: 0.9) < 1)
            ItemType.PORTRAT.ordinal
        else
            ItemType.LANDSCAPE.ordinal
    }

    var images: List<PosterEntity>? = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ImageClickListener {
        fun onImageClicked(image: PosterEntity)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = images?.get(position)
        imageLoader.load(
            url = item?.file_path,
            imageView = holder.image
        )


    }

    override fun getItemCount(): Int {
        return images?.size ?: 0
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
    }

}