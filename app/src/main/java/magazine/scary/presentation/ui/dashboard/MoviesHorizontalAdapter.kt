package magazine.scary.presentation.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.tools.listen
import com.pixabay.utils.views.RateView
import kotlinx.android.synthetic.main.movie_horizontal_item.view.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageModel
import magazine.scary.domain.entities.MovieModel
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class MoviesHorizontalAdapter @Inject constructor() :
    RecyclerView.Adapter<MoviesHorizontalAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var movieClickListener: MovieClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_horizontal_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            movies[pos].let { movieClickListener.onMovieClicked(it) }
        }
    }

    var movies = listOf<MovieModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface MovieClickListener {
        fun onMovieClicked(movie: MovieModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = movies[position]
        imageLoader.load(
            url = item.small_image,
            imageView = holder.image
        )

        holder.rate.rate = item.rate
        holder.movieTitle.text = item.name


    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val rate: RateView = view.rate
        val movieTitle: TextView = view.movieTitle
    }

}