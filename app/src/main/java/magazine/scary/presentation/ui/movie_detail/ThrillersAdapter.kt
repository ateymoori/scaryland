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
import magazine.scary.domain.entities.ImageModel
import magazine.scary.domain.entities.ThrillerModel
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class ThrillersAdapter @Inject constructor() :
    RecyclerView.Adapter<ThrillersAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var clickListener: ClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.thriller_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            items?.get(pos)?.let { clickListener.onThrillerClicked(it) }
        }
    }

    var items:List<ThrillerModel>? = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ClickListener {
        fun onThrillerClicked(thriller: ThrillerModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items?.get(position)
        imageLoader.load(
            url = item?.thumb,
            imageView = holder.image
        )


    }

    override fun getItemCount(): Int {
        return items?.size ?:0
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
          val image: ImageView = view.image
    }

}