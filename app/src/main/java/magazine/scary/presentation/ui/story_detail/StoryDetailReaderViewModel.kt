package magazine.scary.presentation.ui.story_detail

import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.base.BaseViewModel
import magazine.scary.domain.use_cases.GetStory
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