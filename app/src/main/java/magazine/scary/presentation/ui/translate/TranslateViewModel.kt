package magazine.scary.presentation.ui.translate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import magazine.scary.domain.use_cases.Translate
import javax.inject.Inject

class TranslateViewModel @Inject constructor(
    private val translate: Translate
) : BaseViewModel() {

    val translateViewState: MutableLiveData<TranslateViewState> = MutableLiveData()

    init {
        translateViewState.value = TranslateViewState()
    }


      fun translate(language_code: String, word: String) {

          compositeDisposable.add(
              translate.translate(language_code,word).subscribe(
                  {
                      translateViewState.value =
                          translateViewState.value?.copy(showLoading = false, data = it)
                  },{
                      translateViewState.value = translateViewState.value?.copy(showLoading = false)
                  }
              )

          )
    }


}