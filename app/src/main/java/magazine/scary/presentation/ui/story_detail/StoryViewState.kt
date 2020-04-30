package magazine.scary.presentation.ui.story_detail

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity

data class StoryViewState(
    val showLoading: Boolean = true,
    val data: StoryEntity? = null
)