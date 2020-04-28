package magazine.scary.presentation.ui.movie_detail

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
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.use_cases.GetPosters
import magazine.scary.presentation.ui.dashboard.MoviesListViewState
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val mainRepo: MainRepo,
    private val getPosters: GetPosters
) : BaseViewModel() {

    var movie: MovieEntity? = null
    val movieThrillers = MutableLiveData<Response<Any?>>()


      val postersViewState: MutableLiveData<PostersListViewState> = MutableLiveData()

    init {
        postersViewState.value = PostersListViewState()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        if (movie != null) {
            getData(movieID = movie?.id)
        }
    }

    private fun getData(movieID: Int?) {
        compositeDisposable.add(

            getPosters.getPosters(movieID = movieID ?: 0).subscribe({
                postersViewState.value =
                    postersViewState.value?.copy(showLoading = false, data = it)
            },
                {
                    postersViewState.value = postersViewState.value?.copy(showLoading = false)
                })
        )


//        if (movieID != null)
//            viewModelScope.launch {
//                moviePosters.value = Loading(null)
//                moviePosters.value = withContext(Dispatchers.IO) {
//                    Success(data = mainRepo.getMoviePosters(movieID))
//                }
//                movieThrillers.value = withContext(Dispatchers.IO) {
//                    Success(data = mainRepo.getThrillers(movieID))
//                }
//            }
    }


}