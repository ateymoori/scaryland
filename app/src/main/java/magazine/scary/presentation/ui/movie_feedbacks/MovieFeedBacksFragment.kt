package magazine.scary.presentation.ui.movie_feedbacks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_video_details.*
import magazine.scary.R
import magazine.scary.domain.entities.MovieModel
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject
import magazine.scary.presentation.ui.movie_detail.MoviePagerAdapter

class MovieFeedBacksFragment : ExpandedBottomSheetDialog() {

    lateinit var movie: MovieModel

    companion object {
        private var instance: MovieFeedBacksFragment? = null
        fun get(): MovieFeedBacksFragment {
            return (instance
                ?: MovieFeedBacksFragment().also {
                    instance = it
                })
        }
    }

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

        pager.adapter = MoviePagerAdapter(childFragmentManager)
        

    }

}
