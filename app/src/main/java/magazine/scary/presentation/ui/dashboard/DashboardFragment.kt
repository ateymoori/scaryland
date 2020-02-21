package magazine.scary.presentation.ui.dashboard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.log
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import magazine.scary.R
import magazine.scary.domain.entities.ImageModel
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class DashboardFragment : Fragment(), DashboardContract {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imagesAdaptor: ImagesHorizontalAdapter

    private lateinit var viewModel: DashboardViewModel

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

        viewModel.onViewCreated()

        imagesList.adapter = imagesAdaptor


        viewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    imagesAdaptor.images = (it.data as List<ImageModel>).shuffled().take(20)
                }
                else -> {
                    "".log("aaaaaa 2")
                }
            }
        })
    }


}
