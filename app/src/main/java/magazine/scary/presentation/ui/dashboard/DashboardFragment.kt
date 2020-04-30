package magazine.scary.presentation.ui.dashboard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.pixabay.utils.models.Success
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.presentation.ui.container.MainActivity
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.StartSnapHelper
import javax.inject.Inject

class DashboardFragment : Fragment(), ImagesHorizontalAdapter.ImageClickListener,
    MoviesHorizontalAdapter.MovieClickListener, StoriesHorizontalAdapter.StoryClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imagesAdaptor: ImagesHorizontalAdapter

    @Inject
    lateinit var moviesAdapter: MoviesHorizontalAdapter

    @Inject
    lateinit var storiesAdapter: StoriesHorizontalAdapter

    private lateinit var viewModel: DashboardViewModel

    private val snapList = StartSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
                .also { it.onViewCreated() }

        imagesList.adapter = imagesAdaptor.also { it.imageClickListener = this }
        moviesList.adapter = moviesAdapter.also { it.movieClickListener = this }
        storiesList.adapter = storiesAdapter.also { it.storyClickListener = this }

        snapList.attachToRecyclerView(imagesList)
        snapList.attachToRecyclerView(moviesList)
        snapList.attachToRecyclerView(storiesList)

        viewModel.moviesViewState.observe(viewLifecycleOwner, Observer {
            swipeLayout.isRefreshing = it.showLoading
            moviesAdapter.movies = it.data?.take(16)
        })

        viewModel.storiesViewState.observe(viewLifecycleOwner, Observer {
            swipeLayout.isRefreshing = it.showLoading
            storiesAdapter.stories = it.data?.take(16)
        })

        viewModel.imagesViewState.observe(viewLifecycleOwner, Observer {
            swipeLayout.isRefreshing = false
            imagesAdaptor.images = it.data?.take(16)
        })


        viewAllImages.setOnClickListener {
            findNavController(it).navigate(
                R.id.action_dashboardFragment_to_imagesListFrgment
            )
        }

        viewAllMovies.setOnClickListener {
            findNavController(it).navigate(
                R.id.action_dashboardFragment_to_moviesListFragment
            )
        }
        viewAllStories.setOnClickListener {
            findNavController(it).navigate(
                R.id.action_dashboardFragment_to_storyListFragment
            )
        }

        swipeLayout.setOnRefreshListener { viewModel.onViewCreated() }

        menu.setOnClickListener { (activity as? MainActivity)?.toggleMenu() }
    }

    override fun onImageClicked(image: ImageEntity) {
        findNavController(imagesList).navigate(
            R.id.action_dashboardFragment_to_imageViewerFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to image)
        )
    }


    override fun onStoryClicked(story: StoryEntity?) {
        findNavController(moviesList).navigate(
            R.id.action_dashboardFragment_to_storyReaderFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to story)
        )
    }

    override fun onMovieClicked(movie: MovieEntity?) {
        findNavController(moviesList).navigate(
            R.id.action_dashboardFragment_to_videoDetailsFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to movie)
        )
    }


}
