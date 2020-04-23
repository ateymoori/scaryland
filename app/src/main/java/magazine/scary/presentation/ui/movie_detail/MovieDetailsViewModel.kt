package magazine.scary.presentation.ui.movie_detail

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
import magazine.scary.domain.entities.MovieModel
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {

    var movie: MovieModel? = null
    val moviePosters = MutableLiveData<Response<Any?>>()
    val movieThrillers = MutableLiveData<Response<Any?>>()


    override fun onViewCreated() {
        super.onViewCreated()
        if (movie != null) {
            getData(movieID = movie?.id.toString())
        }
    }

    private fun getData(movieID: String?) {
        if (movieID != null)
            viewModelScope.launch {
                moviePosters.value = Loading(null)
                moviePosters.value = withContext(Dispatchers.IO) {
                    Success(data = mainRepo.getMoviePosters(movieID))
                }
                movieThrillers.value = withContext(Dispatchers.IO) {
                    Success(data = mainRepo.getThrillers(movieID))
                }
            }
    }


}