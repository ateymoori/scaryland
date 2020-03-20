package magazine.scary.presentation.ui.movie_posters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Success
import dagger.android.support.AndroidSupportInjection
import magazine.scary.R
import magazine.scary.domain.entities.ImageModel
import magazine.scary.domain.entities.PosterModel
import magazine.scary.presentation.ui.dashboard.ImagesHorizontalAdapter
import magazine.scary.tools.utils.Cons
import javax.inject.Inject
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_movie_posters.*

class MoviePostersFragment @Inject constructor() : Fragment(),
    ImagesHorizontalAdapter.ImageClickListener, PostersHorizontalAdapter.ImageClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MoviePostersViewModel

    @Inject
    lateinit var imagesAdaptor: PostersHorizontalAdapter

    var movieID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_posters, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MoviePostersViewModel::class.java)
        viewModel.onViewCreated()
        observeVM()

        imagesList.adapter = imagesAdaptor.also { it.imageClickListener = this }
        viewModel.getPosters(movieID)

    }

    private fun observeVM() {
        viewModel.moviePosters.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    val data = (it.data as? List<PosterModel>)
                    val mappedData =
                        data?.onEach { }
                            ?.map { ImageModel(webformatURL = "https://image.tmdb.org/t/p/original/${it.file_path}") }
                    imagesAdaptor.images = mappedData

                }
                is Loading -> {
                    //   loading.visibility = View.VISIBLE
                }
                else -> {
                    //  loading.visibility = View.GONE
                }
            }
        })

    }


    override fun onImageClicked(image: ImageModel) {

//        findNavController(imagesList).navigate(
//            R.id.action_moviePostersFragment_to_imageViewerFragment
//            ,
//            bundleOf(Cons.ITEM_BUNDLE to image)
//        )

    }

}
