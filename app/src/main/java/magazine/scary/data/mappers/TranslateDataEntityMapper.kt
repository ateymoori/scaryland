package magazine.scary.data.mappers

import magazine.scary.data.entities.ImageData
import magazine.scary.domain.common.Mapper
import magazine.scary.data.entities.ThrillerData
import magazine.scary.data.entities.TranslateData
import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.entities.ThrillerEntity
import magazine.scary.domain.entities.TranslateEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslateDataEntityMapper @Inject constructor() : Mapper<TranslateData, TranslateEntity>() {
    override fun mapFrom(from: TranslateData): TranslateEntity {
        return TranslateEntity(
            results = from.results
        )
    }
}
