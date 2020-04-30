package magazine.scary.presentation.ui.movies_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.tools.listen
import kotlinx.android.synthetic.main.movie_horizontal_item.view.image
import kotlinx.android.synthetic.main.movies_list_item.view.*
import magazine.scary.R
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class MoviesListAdapter @Inject constructor() :
    RecyclerView.Adapter<MoviesListAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var movieClickListener: MovieClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movies_list_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            movies?.get(pos)?.let { movieClickListener.onMovieClicked(it) }
        }
    }

    var movies:List<MovieEntity>? = listOf<MovieEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface MovieClickListener {
        fun onMovieClicked(movie: MovieEntity)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = movies?.get(position)
        imageLoader.load(
            url = item?.landscape_image,
            imageView = holder.image
        )
        holder.name.text = "${item?.title} (${item?.release_date?.substring(0, 4)})"
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val name: TextView = view.name
    }

}