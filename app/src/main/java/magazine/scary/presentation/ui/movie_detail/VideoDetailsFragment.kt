package magazine.scary.presentation.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.Navigation
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_video_details.*
import kotlinx.android.synthetic.main.fragment_video_details.image
import kotlinx.android.synthetic.main.story_list_item.*
import magazine.scary.R
import magazine.scary.domain.entities.MovieModel
import magazine.scary.domain.entities.VideoPureModel
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.pixabay.utils.tools.log
import kotlinx.android.synthetic.main.fragment_video_details.view.*
import magazine.scary.MovieDescriptionFragment
import magazine.scary.presentation.ui.movie_posters.MoviePagerAdapter
import magazine.scary.presentation.ui.movie_posters.MoviePostersFragment

class VideoDetailsFragment : Fragment() {

    lateinit var movie: MovieModel

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var moviePosterFragment: MoviePostersFragment

    @Inject
    lateinit var movieDescriptionFragment: MovieDescriptionFragment

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

        back.setOnClickListener { activity?.onBackPressed() }

        name.text = "${movie.name} (${movie.year})"
        content.text = movie.description

        imageLoader.load(
            url = movie.wide_image,
            imageView = poster
        )

        imageLoader.load(
            url = movie.small_image,
            imageView = image
        )

        movieDescriptionFragment.text = movie.description

        pager.adapter = MoviePagerAdapter(
            childFragmentManager,
            listOf(movieDescriptionFragment, moviePosterFragment.also { it.movieID=movie.themoviedb_id })
        )

        tabBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.movieDetails -> {
                    pager.currentItem = 0
                }
                R.id.posters -> {
                    pager.currentItem = 1
                }
            }
            true
        }
        play.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_videoDetailsFragment_to_videoViewerFragment
                ,
                bundleOf(
                    Cons.ITEM_BUNDLE to VideoPureModel(
                        url = movie.thriller,
                        title = movie.name
                    )
                )
            )
        }


    }


}
