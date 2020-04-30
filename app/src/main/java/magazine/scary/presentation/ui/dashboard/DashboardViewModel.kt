package magazine.scary.presentation.ui.dashboard

import androidx.lifecycle.MutableLiveData
import magazine.scary.data.MainRepo
import com.pixabay.utils.base.BaseViewModel
import magazine.scary.domain.use_cases.GetImages
import magazine.scary.domain.use_cases.GetMovies
import magazine.scary.domain.use_cases.GetStories
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo,
    private val getMovies: GetMovies,
    private val getStories: GetStories,
    private val getImages: GetImages

) : BaseViewModel() {




    val moviesViewState: MutableLiveData<MoviesListViewState> = MutableLiveData()
    val storiesViewState: MutableLiveData<StoriesListViewState> = MutableLiveData()
    val imagesViewState: MutableLiveData<ImagesListViewState> = MutableLiveData()

    init {
        moviesViewState.value = MoviesListViewState()
        storiesViewState.value = StoriesListViewState()
        imagesViewState.value = ImagesListViewState()
    }

    override fun onViewCreated() {
        super.onViewCreated()

        getMovies()
        getStories()
        getImages()

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


    private fun getImages() {
        compositeDisposable.add(
            getImages.observable().subscribe({
                imagesViewState.value = imagesViewState.value?.copy(showLoading = false, data = it)
            },
                {
                    imagesViewState.value = imagesViewState.value?.copy(showLoading = false)
                })
        )
    }

}