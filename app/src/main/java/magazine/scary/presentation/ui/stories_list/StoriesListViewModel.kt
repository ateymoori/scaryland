package magazine.scary.presentation.ui.stories_list

import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.base.BaseViewModel
import magazine.scary.domain.use_cases.GetStories
import javax.inject.Inject

class StoriesListViewModel @Inject constructor(
    private val getStories: GetStories
) : BaseViewModel() {


    val storiesViewState: MutableLiveData<StoriesViewState> = MutableLiveData()

    init {
        storiesViewState.value = StoriesViewState()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getList()
    }

    private fun getList() {
        compositeDisposable.add(
            getStories.observable().subscribe({
                storiesViewState.value =
                    storiesViewState.value?.copy(showLoading = false, data = it)
            }, {
                storiesViewState.value = storiesViewState.value?.copy(showLoading = false)
            })
        )
    }


}