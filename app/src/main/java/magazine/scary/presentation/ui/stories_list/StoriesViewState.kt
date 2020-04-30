package magazine.scary.presentation.ui.stories_list

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity

data class StoriesViewState(
    val showLoading: Boolean = true,
    val data: List<StoryEntity>? = null
)