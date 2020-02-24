package magazine.scary.presentation.ui.dashboard

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

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {


    val imagesResults = MutableLiveData<Response<Any?>>()
    val moviesResults = MutableLiveData<Response<Any?>>()
    val storiesResults = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
        getImages(Cons.DEFAULT_SEARCH_WORD)
        getVideos()
        getStories()
    }

    private fun getImages(word: String) {
        viewModelScope.launch {
            imagesResults.value = Loading(null)
            imagesResults.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.getImages(word))
            }
        }
    }

    private fun getVideos() {
        viewModelScope.launch {
            moviesResults.value = Loading(null)
            moviesResults.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.getMovies())
            }
        }
    }

    private fun getStories() {
        viewModelScope.launch {
            storiesResults.value = Loading(null)
            storiesResults.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.getStories())
            }
        }
    }


//    private fun search(word:String): Flow<List<ImageModel>> = flow {
//        emit(rest.getImages(word).hits)
//    }
//
//    fun getImages(word:String): LiveData<List<ImageModel>> {
//        return search(word).asLiveData()
//    }
}