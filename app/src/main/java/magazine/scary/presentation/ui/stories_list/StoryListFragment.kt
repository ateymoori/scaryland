package magazine.scary.presentation.ui.stories_list


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
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_story_list.*
import magazine.scary.R
import magazine.scary.domain.entities.StoryModel
import magazine.scary.tools.utils.Cons
import javax.inject.Inject


class StoryListFragment : Fragment(), StoriesListAdapter.StoryClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: StoriesListViewModel
    @Inject
    lateinit var storiesAdaptor: StoriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_story_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storiesList.layoutManager = GridLayoutManager(activity as Context?, 2)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StoriesListViewModel::class.java)

        viewModel.onViewCreated()

        storiesList.adapter = storiesAdaptor.also { it.storyClickListener=this }

        observeVM()
    }

    private fun observeVM(){
        viewModel.storiesResults.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> storiesAdaptor.stories =
                    (it.data as List<StoryModel>)
                else -> {
                }
            }
        })
    }

    override fun onStoryClickListener(story: StoryModel) {
        Navigation.findNavController(storiesList).navigate(
            R.id.action_storyListFragment_to_storyReaderFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to story)
        )
    }
}
