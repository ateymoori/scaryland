package magazine.scary.presentation.ui.dashboard

import magazine.scary.domain.entities.ImageEntity

data class ImagesListViewState(
    val showLoading: Boolean = true,
    val data: List<ImageEntity>? = null
)