package magazine.scary.presentation.ui.movie_detail

import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Response
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.use_cases.GetPosters
import magazine.scary.domain.use_cases.GetThrillers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getPosters: GetPosters,
    private val getThrillers: GetThrillers
) : BaseViewModel() {

    var movie: MovieEntity? = null
    val movieThrillers = MutableLiveData<Response<Any?>>()


    val postersViewState: MutableLiveData<PostersListViewState> = MutableLiveData()
    val thrillersViewState: MutableLiveData<ThrillersListViewState> = MutableLiveData()

    init {
        postersViewState.value = PostersListViewState()
        thrillersViewState.value = ThrillersListViewState()
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

        compositeDisposable.add(
            getThrillers.getThrillers(movieID = movieID ?: 0).subscribe({
                thrillersViewState.value =
                    thrillersViewState.value?.copy(showLoading = false, data = it)
            },
                {
                    thrillersViewState.value = thrillersViewState.value?.copy(showLoading = false)
                })
        )


    }


}