package magazine.scary.presentation.ui.dashboard

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity

data class StoriesListViewState(
    val showLoading: Boolean = true,
    val data: List<StoryEntity>? = null
)