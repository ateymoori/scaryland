package magazine.scary

import io.reactivex.Observable
import magazine.scary.domain.common.DomainTestUtils
import magazine.scary.domain.common.DomainTestUtils.Companion.getImagesList
import magazine.scary.domain.common.DomainTestUtils.Companion.getStoriesList
import magazine.scary.domain.common.TestTransformer
import magazine.scary.domain.entities.TranslateEntity
import magazine.scary.domain.interfaces.ImageRepository
import magazine.scary.domain.interfaces.StoryRepository
import magazine.scary.domain.interfaces.TranslateRepository
import magazine.scary.domain.use_cases.GetImages
import magazine.scary.domain.use_cases.GetStories
import magazine.scary.domain.use_cases.Translate
import org.junit.Test
import org.mockito.Mockito

class UseCasesTest {


    @Test
    fun getImages() {
        val imageRepository = Mockito.mock(ImageRepository::class.java)
        Mockito.`when`(imageRepository.getImages()).thenReturn(Observable.just(getImagesList(10)))

        val getImages = GetImages(TestTransformer(), imageRepository)

        getImages.observable().test()
            .assertValue { results -> results.size == 10 }
            .assertComplete()
    }


    @Test
    fun getStories() {
        val storyRepository = Mockito.mock(StoryRepository::class.java)
        Mockito.`when`(storyRepository.getStories()).thenReturn(Observable.just(getStoriesList(10)))

        val getStories = GetStories(TestTransformer(), storyRepository)

        getStories.observable().test()
            .assertValue { results -> results.size == 10 }
            .assertComplete()
    }


    @Test
    fun getPosters() {
        val storyRepository = Mockito.mock(StoryRepository::class.java)
        Mockito.`when`(storyRepository.getStories()).thenReturn(Observable.just(getStoriesList(10)))

        val getStories = GetStories(TestTransformer(), storyRepository)

        getStories.observable().test()
            .assertValue { results -> results.size == 10 }
            .assertComplete()
    }


    @Test
    fun translate() {
        val translateRepository = Mockito.mock(TranslateRepository::class.java)

        Mockito.`when`(translateRepository.translate("fa", "Hi")).thenReturn(
            Observable.just(
                TranslateEntity(results = "سلام")
            )
        )

        val translate = Translate(TestTransformer(), translateRepository)

        translate.translate(language_code = "fa", word = "Hi").test()
            .assertValue { it.results.contentEquals("سلام") }
            .assertComplete()

    }


}