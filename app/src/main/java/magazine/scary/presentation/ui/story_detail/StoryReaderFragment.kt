package magazine.scary.presentation.ui.story_detail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pixabay.utils.models.Success
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
        viewModel.objectID = story.objectId
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

        observeVM()
    }

    private fun observeVM() {
        viewModel.storyDetail.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success ->{
                    var txt = ((it.data as StoryModel).content_a).replace(".", ".\n")
                    txt += ((it.data as StoryModel).content_b).replace(".", ".\n")
                    content.text = txt
                }


                else -> {
                }
            }
        })

    }

}
