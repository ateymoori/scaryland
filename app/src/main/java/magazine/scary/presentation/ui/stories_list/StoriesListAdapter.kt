package magazine.scary.presentation.ui.stories_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.tools.listen
import kotlinx.android.synthetic.main.story_horizontal_item.view.*
import magazine.scary.R
import magazine.scary.domain.entities.MovieModel
import magazine.scary.domain.entities.StoryModel
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class StoriesListAdapter @Inject constructor() :
    RecyclerView.Adapter<StoriesListAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var storyClickListener: StoryClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    var stories = listOf<StoryModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.story_list_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            stories[pos].let { storyClickListener.onStoryClickListener(it) }
        }
    }


    interface StoryClickListener {
        fun onStoryClickListener(story: StoryModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = stories[position]
        imageLoader.load(
            url = item.image,
            imageView = holder.image
        )
        imageLoader.load(
            url = item.author?.image,
            imageView = holder.avatar
        )
        holder.title.text = "${item.title}"
        holder.readingTime.text = "${item.reading_time}"
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val avatar: ImageView = view.avatar
        val title: TextView = view.title
        val readingTime: TextView = view.readingTime
    }

}