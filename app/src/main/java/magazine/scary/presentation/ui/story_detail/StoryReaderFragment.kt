package magazine.scary.presentation.ui.story_detail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.AppUtils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_story_reader.*
import magazine.scary.R
import magazine.scary.domain.entities.MovieModel
import magazine.scary.domain.entities.StoryModel
import magazine.scary.presentation.ui.images_list.ImagesListViewModel
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject


class StoryReaderFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: StoryDetailReaderViewModel
    lateinit var story: StoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        arguments?.let {
            story = it.getParcelable(Cons.ITEM_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_story_reader, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StoryDetailReaderViewModel::class.java)
        viewModel.fileAddress = story.content_file
        viewModel.onViewCreated()
        imageLoader.load(
            url = story.image,
            imageView = image
        )
        imageLoader.load(
            url = story.author?.image,
            imageView = avatar
        )
        title.text = story.title
        author.text = "by ${story.author?.name} "
        back.setOnClickListener { activity?.onBackPressed() }
        share.setOnClickListener {
            AppUtils.shareToMessagingApps(
                activity,
                "Share Story ${story.title}",
                "Download ScaryLand and read this story."
            )
        }
        observeVM()
    }

    private fun observeVM() {
        viewModel.storyDetail.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    loading.visibility = View.GONE
                    content.text = (it.data as String)
                    //content.setup(content.text)
                }
                is Loading -> {
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    loading.visibility = View.GONE
                }
            }
        })

    }

}
