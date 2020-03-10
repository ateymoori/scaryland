package magazine.scary.presentation.ui.translate

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
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class TranslateViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {


    val translateResult = MutableLiveData<Response<Any?>>()


      fun translate(language_code: String, word: String) {
        viewModelScope.launch {
            translateResult.value = Loading(null)
            translateResult.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.translate(language_code, word).string())
            }
        }
    }


}