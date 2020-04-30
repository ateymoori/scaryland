package magazine.scary.presentation.ui.movies_list

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
import magazine.scary.domain.use_cases.GetMovies
import magazine.scary.tools.utils.Cons
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