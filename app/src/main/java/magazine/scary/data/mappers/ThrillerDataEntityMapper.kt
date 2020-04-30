package magazine.scary.data.mappers

import magazine.scary.domain.common.Mapper
import magazine.scary.data.entities.ThrillerData
import magazine.scary.domain.entities.ThrillerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThrillerDataEntityMapper @Inject constructor() : Mapper<ThrillerData, ThrillerEntity>() {
    override fun mapFrom(from: ThrillerData): ThrillerEntity {
        return ThrillerEntity(
            thumb = from.thumb,
            video = from.video
        )
    }
}
