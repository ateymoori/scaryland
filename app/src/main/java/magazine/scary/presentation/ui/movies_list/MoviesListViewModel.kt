package magazine.scary.presentation.ui.movies_list

import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.base.BaseViewModel
import magazine.scary.domain.use_cases.GetMovies
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val getMovies: GetMovies
) : BaseViewModel() {

    val moviesViewState: MutableLiveData<MoviesViewState> = MutableLiveData()

    init {
        moviesViewState.value = MoviesViewState()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getMovies()
    }
    fun getMovies() {
            compositeDisposable.add(
                getMovies.observable().subscribe({
                    moviesViewState.value =
                        moviesViewState.value?.copy(showLoading = false, data = it)
                },
                    {
                        moviesViewState.value = moviesViewState.value?.copy(showLoading = false)
                    })
            )
    }





}