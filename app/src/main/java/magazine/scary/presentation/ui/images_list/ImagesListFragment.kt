package magazine.scary.presentation.ui.images_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.pixabay.utils.models.Success
import com.pixabay.utils.views.StartSnapHelper
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageModel
import magazine.scary.presentation.ui.dashboard.ImagesHorizontalAdapter
import magazine.scary.tools.utils.Cons.Companion.ITEM_BUNDLE
import javax.inject.Inject

class ImagesListFragment : Fragment(), ImagesHorizontalAdapter.ImageClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ImagesListViewModel
    @Inject
    lateinit var imagesAdaptor: ImagesHorizontalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesList.layoutManager = GridLayoutManager(activity as Context?, 3)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ImagesListViewModel::class.java)

        viewModel.onViewCreated()

        imagesList.adapter = imagesAdaptor.also { it.imageClickListener=this }

        observeVM()
    }

    private fun observeVM(){
        viewModel.imagesResults.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> imagesAdaptor.images =
                    (it.data as List<ImageModel>)
                else -> {
                }
            }
        })
    }

    override fun onImageClicked(image: ImageModel) {
        Navigation.findNavController(imagesList).navigate(
            R.id.action_imagesListFrgment_to_imageViewerFragment
        ,
            bundleOf(ITEM_BUNDLE to image)
        )
    }
}
