package magazine.scary.presentation.ui.story_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import magazine.scary.data.MainRepo
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import magazine.scary.domain.use_cases.GetStory
import magazine.scary.presentation.ui.dashboard.MoviesListViewState
import magazine.scary.presentation.ui.dashboard.StoriesListViewState
import javax.inject.Inject

class StoryDetailReaderViewModel @Inject constructor(
    private val getStory: GetStory
) : BaseViewModel() {

    lateinit var id: String

    val storyViewState: MutableLiveData<StoryViewState> = MutableLiveData()

    init {
        storyViewState.value = StoryViewState()
    }

    fun getStory(id: Int?) {
        if (id != null)
            compositeDisposable.add(
                getStory.getStory(id).subscribe({
                    storyViewState.value =
                        storyViewState.value?.copy(showLoading = false, data = it)
                },
                    {
                        storyViewState.value = storyViewState.value?.copy(showLoading = false)
                    })
            )
    }

}