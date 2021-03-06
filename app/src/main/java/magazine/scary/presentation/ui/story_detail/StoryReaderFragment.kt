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
import com.pixabay.utils.rxbus.RxBus
import com.pixabay.utils.rxbus.RxEvent
import com.pixabay.utils.tools.AppUtils
import dagger.android.support.AndroidSupportInjection
import dm.audiostreamer.AudioStreamingManager
import dm.audiostreamer.CurrentSessionCallback
import dm.audiostreamer.MediaMetaData
import kotlinx.android.synthetic.main.fragment_story_reader.*
import magazine.scary.R
import com.pixabay.utils.models.AudioModel
import com.pixabay.utils.models.AudioStatus
import com.pixabay.utils.tools.log
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.tools.utils.Cons
import magazine.scary.tools.utils.ImageLoader
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min


class StoryReaderFragment : Fragment(), CurrentSessionCallback {

    private lateinit var streamingManager: AudioStreamingManager

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: StoryDetailReaderViewModel
    var story: StoryEntity? = null

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
        viewModel.getStory(story?.id)
        imageLoader.load(
            url = story?.image,
            imageView = image
        )
        imageLoader.load(
            url = story?.author_image,
            imageView = avatar
        )
        title.text = story?.title
        author.text = "by ${story?.author_name_family} "
        back.setOnClickListener { activity?.onBackPressed() }
        share.setOnClickListener {
            AppUtils.shareToMessagingApps(
                activity,
                "Share Story ",
                "Download ScaryLand and read this story."
            )
            AppUtils.shareToMessagingApps(
                activity, title = "Hi there!",
                message = "Install Horror Magazine to read ${story?.title} by ${story?.author_name_family} . \n https://play.google.com/store/apps/details?id=magazine.scary"
            )
        }
        observeVM()
        handleSelectText()


        //audio
        play.setOnClickListener {
            RxBus.publish(
                RxEvent.AudioPlay(
                    AudioModel(
                        storyID = story?.id.toString(),
                        audioURL = story?.mp3_file,
                        image = story?.image,
                        title = story?.title,
                        subTitle = story?.author_name_family
                    )
                )
            )
            RxBus.publish(
                RxEvent.AudioControl(
                    AudioStatus.PLAY
                )
            )
        }

    }


    private fun observeVM() {
        viewModel.storyViewState.observe(viewLifecycleOwner, Observer {
            showLoading(it.showLoading)
            story = it.data
            showData(story)
        })

    }

    private fun showLoading(show: Boolean) {
        loading.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showData(story: StoryEntity?) {
        content.text = story?.content
        if (story?.mp3_file == null) {
            play.visibility = View.GONE
        } else
            play.visibility = View.VISIBLE
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

    private fun translate(word: String) {
        findNavController(content).navigate(
            R.id.action_storyReaderFragment_to_translateFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to word)
        )
    }

    override fun currentSeekBarPosition(progress: Int) {

    }

    override fun playSongComplete() {
    }

    override fun playNext(indexP: Int, currentAudio: MediaMetaData?) {
    }

    override fun updatePlaybackState(state: Int) {
    }

    override fun playCurrent(indexP: Int, currentAudio: MediaMetaData?) {
    }

    override fun playPrevious(indexP: Int, currentAudio: MediaMetaData?) {
    }
}
