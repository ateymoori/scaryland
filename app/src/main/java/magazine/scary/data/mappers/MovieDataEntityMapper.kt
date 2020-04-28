package magazine.scary.data.mappers

import magazine.scary.domain.common.Mapper
import magazine.scary.data.entities.MovieData
import magazine.scary.domain.entities.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataEntityMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {

    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
            id = from.id,
            title = from.title,
            overview = from.overview,
            imdb = from.imdb,
            genres = from.genres,
            awards = from.awards,
            release_date = from.release_date,
            rotten_tomato = from.rotten_tomato,
            runetime = from.runetime,
            portrait_image = from.portrait_image,
            landscape_image = from.landscape_image,
            themoviedb_id = from.themoviedb_id,
            created_at = from.created_at
        )
    }
}
