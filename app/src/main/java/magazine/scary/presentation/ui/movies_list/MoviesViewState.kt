package magazine.scary.presentation.ui.movies_list

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity

data class MoviesViewState(
    val showLoading: Boolean = true,
    val data: List<MovieEntity>? = null
)