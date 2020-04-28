package magazine.scary.presentation.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.tools.listen
import com.pixabay.utils.tools.log
import kotlinx.android.synthetic.main.image_portrait_item.view.*
import kotlinx.android.synthetic.main.image_portrait_item.view.image
import kotlinx.android.synthetic.main.story_horizontal_item.view.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageModel
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.StoryModel
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class StoriesHorizontalAdapter @Inject constructor() :
    RecyclerView.Adapter<StoriesHorizontalAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var storyClickListener: StoryClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.story_horizontal_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            stories?.get(pos).let { storyClickListener.onStoryClicked(it) }
        }
    }

    var stories: List<StoryEntity>? = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface StoryClickListener {
        fun onStoryClicked(story: StoryEntity?)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = stories?.get(position)
        imageLoader.load(
            url = item?.image,
            imageView = holder.image
        )

        imageLoader.load(
            url = item?.author_image,
            imageView = holder.avatar
        )
        holder.title.text = item?.title
        holder.author.text = item?.author_name_family
        holder.readingTime.text = "${item?.reading_minutes} Min"

        if (item?.mp3_file == null) {
            holder.hasAudio.visibility = View.GONE
        } else
            holder.hasAudio.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return stories?.size ?: 0
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val avatar: ImageView = view.avatar
        val title: TextView = view.title
        val author: TextView = view.author
        val readingTime: TextView = view.readingTime
        val hasAudio: View = view.hasAudio
    }

}