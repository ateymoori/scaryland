package magazine.scary.presentation.ui.stories_list

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
import javax.inject.Inject

class StoriesListViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {


    val storiesResults = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
      //  getStories( )
    }

//    private fun getStories( ) {
//        viewModelScope.launch {
//            storiesResults.value = Loading(null)
//            storiesResults.value = withContext(Dispatchers.IO) {
//                Success(data = mainRepo.getStories( ))
//            }
//        }
//    }


}