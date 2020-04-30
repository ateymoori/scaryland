package magazine.scary.data.mappers

import magazine.scary.domain.common.Mapper
import magazine.scary.data.entities.MovieData
import magazine.scary.data.entities.PosterData
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PosterDataEntityMapper @Inject constructor() : Mapper<PosterData, PosterEntity>() {
    override fun mapFrom(from: PosterData): PosterEntity {
        return PosterEntity(
            aspect_ratio = from.aspect_ratio ,
            file_path = from.file_path ,
            height = from.height ,
            width = from.width
        )
    }
}
