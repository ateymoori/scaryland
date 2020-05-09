package magazine.scary.domain.common

import magazine.scary.domain.entities.ImageEntity
import magazine.scary.domain.entities.MovieEntity
import magazine.scary.domain.entities.PosterEntity
import magazine.scary.domain.entities.StoryEntity

class DomainTestUtils {

    companion object {


        fun getTestStoryEntry(id: Int): StoryEntity {
            return StoryEntity(
                id = id,
                title = "Story$id",
                content = "Story$id",
                image = "http://amirteymoori.ir/voyager/public/storage/authors/April2020/use4EsZrILb8MBddnTi8.jpg",
                mp3_file = "http://amirteymoori.ir/voyager/public/storage/stories/May2020/iFS7xEQqtKSMTk3sW1Az.mp3",
                reading_minutes = id.toLong(),
                author_id = id,
                author_name_family = "Author $id",
                author_description = "Author $id Description",
                author_image = "http://amirteymoori.ir/voyager/public/storage/authors/April2020/use4EsZrILb8MBddnTi8.jpg"
            )
        }

        fun getStoriesList(numberOfEntities: Int): List<StoryEntity> {
            return (1..numberOfEntities).map { getTestStoryEntry(it) }
        }


        fun getTestImageEntity(id: Int): ImageEntity {
            return ImageEntity(
                id = id,
                tags = "Image$id tags",
                previewURL = "Image$id previewURL",
                largeImageURL = "Image$id largeImageURL",
                imageSize = "Image$id imageSize",
                views = id,
                likes = id,
                user = "Image$id user",
                userImageURL = "Image$id userImageURL"
            )
        }

        fun getImagesList(numberOfEntities: Int): List<ImageEntity> {
            return (1..numberOfEntities).map { getTestImageEntity(it) }
        }


        fun getTestPosterEntry(): PosterEntity {
            return PosterEntity(
                aspect_ratio = 1.6,
                file_path = "http://amirteymoori.ir/voyager/public/storage/authors/April2020/use4EsZrILb8MBddnTi8.jpg",
                height = 100,
                width = 160
            )
        }

        fun getPostersList(numberOfEntities: Int): List<PosterEntity> {
            return (1..numberOfEntities).map { getTestPosterEntry() }
        }




    }
}
