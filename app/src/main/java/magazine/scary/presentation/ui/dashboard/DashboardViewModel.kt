package magazine.scary.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pixabay.repo.repo.MainRepo
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import com.pixabay.utils.tools.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import magazine.scary.domain.entities.ImageModel
import magazine.scary.repository.rest.RestService
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel<DashboardContract>() {


    val result = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
        search(Cons.DEFAULT_SEARCH_WORD)
    }
    fun search(word: String) {
        viewModelScope.launch {
            result.value = Loading(null)
            result.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.search(word))
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