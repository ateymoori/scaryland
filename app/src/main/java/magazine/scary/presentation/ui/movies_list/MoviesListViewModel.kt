package magazine.scary.presentation.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class MoviesListViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {


    val imagesResults = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
        getImages(Cons.DEFAULT_SEARCH_WORD)
    }

    private fun getImages(word: String) {
        viewModelScope.launch {
            imagesResults.value = Loading(null)
            imagesResults.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.getMovies( ))
            }
        }
    }


}