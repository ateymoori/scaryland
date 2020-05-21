package magazine.scary.data.mappers

import magazine.scary.data.entities.AuthorData
import magazine.scary.domain.common.Mapper
import magazine.scary.data.entities.MovieData
import magazine.scary.data.entities.PosterData
import magazine.scary.data.entities.StoryData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.StoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryEntityDataMapper @Inject constructor() : Mapper<StoryEntity, StoryData>() {
    override fun mapFrom(from: StoryEntity): StoryData {
        val author = AuthorData(
            id = from.author_id ?: 0,
            name_family = from.author_name_family ?: "",
            description = from.author_description ?: "",
            image = from.author_image ?: ""
        )
        return StoryData(
            id = from.id,
            title = from.title,
            content = from.content,
            image = from.image,
            mp3_file = from.mp3_file,
            reading_mintues = from.reading_minutes,
            author = author
        )
    }
}
