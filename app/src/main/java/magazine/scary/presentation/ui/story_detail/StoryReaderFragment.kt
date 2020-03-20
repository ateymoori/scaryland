package magazine.scary.presentation.ui.story_detail


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
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
import kotlin.math.max
import kotlin.math.min


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
                "Share Story ",
                "Download ScaryLand and read this story."
            )
            AppUtils.shareToMessagingApps(
                activity, title = "Hi there!",
                message = "Install Horror Magazine to read ${story.title} by ${story.author?.name} . \n https://play.google.com/store/apps/details?id=magazine.scary"
            )
        }
        observeVM()
        handleSelectText()
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


    private fun handleSelectText() {
        content.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                menu.removeItem(android.R.id.selectAll)
                menu.removeItem(android.R.id.cut)
                menu.removeItem(android.R.id.copy)
                return true
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                menu.add(0, 100, 0, "Translate").setIcon(R.drawable.circle_black)
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode) {
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                when (item.itemId) {
                    100 -> {
                        var min = 0
                        var max = content.text.length
                        if (content.isFocused) {
                            val selStart = content.selectionStart
                            val selEnd = content.selectionEnd

                            min = max(0, min(selStart, selEnd))
                            max = max(0, max(selStart, selEnd))
                        }
                        val selectedText = content.text.subSequence(min, max)
                         translate(selectedText.toString())
                        mode.finish()
                        return true
                    }
                    else -> {
                    }
                }
                return false
            }

        }
    }

    private fun translate(word:String){
        findNavController(content).navigate(
            R.id.action_storyReaderFragment_to_translateFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to word)
        )
    }
}
