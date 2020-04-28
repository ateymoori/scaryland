package magazine.scary.data.mappers

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
class StoryDataEntityMapper @Inject constructor() : Mapper<StoryData, StoryEntity>() {
    override fun mapFrom(from: StoryData): StoryEntity {
        return StoryEntity(
            id = from.id,
            title = from.title,
            content = from.content,
            image = from.image,
            mp3_file = from.mp3_file,
            reading_minutes = from.reading_mintues,
            author_id = from.author?.id,
            author_name_family = from.author?.name_family,
            author_description = from.author?.description,
            author_image = from.author?.image
        )
    }
}
