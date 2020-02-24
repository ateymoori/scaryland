package magazine.scary.presentation.ui.story_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import magazine.scary.repository.MainRepo
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class StoryDetailReaderViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {

    lateinit var objectID: String

    val storyDetail = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
        getStory(objectID)
    }


    private fun getStory(objectID: String) {
        viewModelScope.launch {
            storyDetail.value = Loading(null)
            storyDetail.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.getStory(objectID))
            }
        }
    }


}