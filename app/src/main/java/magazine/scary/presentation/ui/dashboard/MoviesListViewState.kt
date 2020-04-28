package magazine.scary.presentation.ui.dashboard

import magazine.scary.domain.entities.MovieEntity

data class MoviesListViewState(
    val showLoading: Boolean = true,
    val data: List<MovieEntity>? = null
)