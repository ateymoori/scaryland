package magazine.scary.presentation.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import magazine.scary.data.MainRepo
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import magazine.scary.domain.use_cases.GetMovies
import magazine.scary.domain.use_cases.GetStories
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo,
    private val getMovies: GetMovies,
    private val getStories: GetStories

) : BaseViewModel() {


    val imagesResults = MutableLiveData<Response<Any?>>()
    val storiesResults = MutableLiveData<Response<Any?>>()


    val moviesViewState: MutableLiveData<MoviesListViewState> = MutableLiveData()
    val storiesViewState: MutableLiveData<StoriesListViewState> = MutableLiveData()

    init {
        moviesViewState.value = MoviesListViewState()
        storiesViewState.value = StoriesListViewState()
    }

    override fun onViewCreated() {
        super.onViewCreated()

        getMovies()
        getStories()

//        getImages(Cons.DEFAULT_SEARCH_WORD)
//        getVideos()
//        getStories()
    }


    private fun getMovies() {
        compositeDisposable.add(
            getMovies.observable().subscribe({
                moviesViewState.value = moviesViewState.value?.copy(showLoading = false, data = it)
            },
                {
                    moviesViewState.value = moviesViewState.value?.copy(showLoading = false)
                })
        )
    }


    private fun getStories() {
        compositeDisposable.add(
            getStories.observable().subscribe({
                storiesViewState.value =
                    storiesViewState.value?.copy(showLoading = false, data = it)
            },
                {
                    storiesViewState.value = storiesViewState.value?.copy(showLoading = false)
                })
        )
    }


    private fun getImages(word: String) {
        viewModelScope.launch {
            imagesResults.value = Loading(null)
            imagesResults.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.getImages(word))
            }
        }
    }

//    private fun getStories() {
//        viewModelScope.launch {
//            storiesResults.value = Loading(null)
//            storiesResults.value = withContext(Dispatchers.IO) {
//                Success(data = mainRepo.getStories())
//            }
//        }
//    }


//    private fun search(word:String): Flow<List<ImageModel>> = flow {
//        emit(rest.getImages(word).hits)
//    }
//
//    fun getImages(word:String): LiveData<List<ImageModel>> {
//        return search(word).asLiveData()
//    }
}