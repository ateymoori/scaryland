package magazine.scary.presentation.ui.movie_detail

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity

data class PostersListViewState(
    val showLoading: Boolean = true,
    val data: List<PosterEntity>? = null
)