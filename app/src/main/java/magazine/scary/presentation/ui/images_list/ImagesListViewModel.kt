package magazine.scary.presentation.ui.images_list

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
import magazine.scary.domain.use_cases.GetImages
import magazine.scary.presentation.ui.dashboard.ImagesListViewState
import magazine.scary.tools.utils.Cons
import javax.inject.Inject

class ImagesListViewModel @Inject constructor(
    private val getImages: GetImages
) : BaseViewModel() {


    val imagesViewState: MutableLiveData<ImagesListViewState> = MutableLiveData()

    init {
        imagesViewState.value = ImagesListViewState()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getImages()
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