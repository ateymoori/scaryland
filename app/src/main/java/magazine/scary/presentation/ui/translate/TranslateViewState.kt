package magazine.scary.presentation.ui.translate

import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.StoryEntity
import magazine.scary.domain.entities.TranslateEntity

data class TranslateViewState(
    val showLoading: Boolean = true,
    val data: TranslateEntity? = null
)