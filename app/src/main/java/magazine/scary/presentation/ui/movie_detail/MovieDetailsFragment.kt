package magazine.scary.presentation.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.pixabay.utils.models.Success
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.image
import magazine.scary.R
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.*
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject
import magazine.scary.tools.utils.StartSnapHelper

class MovieDetailsFragment : Fragment(), PostersHorizontalAdapter.ImageClickListener,
    ThrillersAdapter.ClickListener {

    lateinit var movie: MovieEntity

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieDetailsViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var postersAdapter: PostersHorizontalAdapter

    @Inject
    lateinit var imagesAdapter: PostersHorizontalAdapter

    @Inject
    lateinit var thrillersAdapter: ThrillersAdapter

    private val snapList1 = StartSnapHelper()

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
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
                .apply { movie = this@MovieDetailsFragment.movie }
        viewModel.onViewCreated()
        observeVM()


        back.setOnClickListener { activity?.onBackPressed() }

        name.text = "${movie.title} (${movie.release_date.substring(0, 4)})"
        imdb.text = "${movie.imdb}/10"
        genres.text = movie.genres
        rotten_tomato.text = movie.rotten_tomato
        runTime.text = movie.runetime
        content.text = movie.overview

        postersList.adapter = postersAdapter.also { it.imageClickListener = this }
        imagesList.adapter = imagesAdapter.also { it.imageClickListener = this }
        thrillersList.adapter = thrillersAdapter.also { it.clickListener = this }
        snapList1.attachToRecyclerView(postersList)
        snapList1.attachToRecyclerView(imagesList)
        snapList1.attachToRecyclerView(thrillersList)

        imageLoader.load(
            url = movie.landscape_image,
            imageView = poster
        )

        imageLoader.load(
            url = movie.portrait_image,
            imageView = image
        )


    }

    private fun observeVM() {
        viewModel.postersViewState.observe(viewLifecycleOwner, Observer {
            postersAdapter.images =
               it.data?.filter { it.aspect_ratio < 1 }
            imagesAdapter.images =
                it.data?.filter { it.aspect_ratio >= 1 }

        })

        viewModel.movieThrillers.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    thrillersAdapter.items =
                        (it.data as? List<ThrillerModel>)
                }
            }
        })

    }

    override fun onImageClicked(image: PosterEntity) {
//        NavHostFragment.findNavController(this).navigate(
//            R.id.action_videoDetailsFragment_to_imageViewerFragment
//            ,
//            bundleOf(
//                Cons.ITEM_BUNDLE to image.mapToImageModel()
//            )
//        )

    }

    override fun onThrillerClicked(thriller: ThrillerModel) {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_videoDetailsFragment_to_videoViewerFragment
            ,
            bundleOf(
                Cons.ITEM_BUNDLE to VideoPureModel(
                    url = thriller.video,
                    title = movie.title
                )
            )
        )

    }


}
