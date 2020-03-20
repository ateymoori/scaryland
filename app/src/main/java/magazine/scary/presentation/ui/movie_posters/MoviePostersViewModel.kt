package magazine.scary.presentation.ui.movie_posters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import magazine.scary.repository.MainRepo
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import magazine.scary.presentation.ui.dashboard.ImagesHorizontalAdapter
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class MoviePostersViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {

    val moviePosters = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
    }


    fun getPosters(movieID: String?) {
        if (movieID != null)
            viewModelScope.launch {
                moviePosters.value = Loading(null)
                moviePosters.value = withContext(Dispatchers.IO) {
                    Success(data = mainRepo.getMoviePosters(movieID).backdrops)
                }
            }
    }


}