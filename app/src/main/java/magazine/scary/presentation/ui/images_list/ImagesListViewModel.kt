package magazine.scary.presentation.ui.images_list

import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.base.BaseViewModel
import magazine.scary.domain.use_cases.GetImages
import magazine.scary.presentation.ui.dashboard.ImagesListViewState
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