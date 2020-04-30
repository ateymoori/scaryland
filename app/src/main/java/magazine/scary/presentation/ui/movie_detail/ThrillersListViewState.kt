package magazine.scary.presentation.ui.movie_detail

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.ThrillerEntity

data class ThrillersListViewState(
    val showLoading: Boolean = true,
    val data: List<ThrillerEntity>? = null
)