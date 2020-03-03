package magazine.scary.presentation.ui.movie_detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pixabay.utils.base.BaseBottomSheetFragment
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_video_details.*
import kotlinx.android.synthetic.main.fragment_video_details.view.*
import magazine.scary.R
import magazine.scary.domain.entities.MovieModel
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject

class VideoDetailsFragment : ExpandedBottomSheetDialog() {

    lateinit var movie: MovieModel
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        arguments?.let {
            movie = it.getParcelable(Cons.ITEM_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader.load(
            url = movie.wide_image,
            imageView = poster
        )

        imageLoader.load(
            url = movie.small_image,
            imageView = image
        )

        play.setOnClickListener {
            poster.visibility = View.GONE
            play.visibility = View.GONE
        }

        name.text = "${movie.name} (${movie.year})"
        content.text = movie.description

        lifecycle.addObserver(trailer)

        trailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = movie.thriller ?: ""
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.pause()
            }
        })
        back.setOnClickListener { dismiss() }
    }

}
