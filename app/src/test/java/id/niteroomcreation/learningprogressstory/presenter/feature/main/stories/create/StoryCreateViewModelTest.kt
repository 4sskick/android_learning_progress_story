package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepository
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.util.DataDummy
import id.niteroomcreation.learningprogressstory.util.MainCoroutineRule
import id.niteroomcreation.learningprogressstory.util.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

/**
 * Created by Septian Adi Wijaya on 18/02/2023.
 * please be sure to add credential if you use people's code
 */
@RunWith(MockitoJUnitRunner::class)
class StoryCreateViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcher = MainCoroutineRule()


    @Mock
    private lateinit var storyRepository: StoriesRepository
    private lateinit var createViewModel: StoryCreateViewModel

    //image multipart body
    private val file: File = Mockito.mock(File::class.java)
    private val requestImageFile: RequestBody = file.asRequestBody("image/jpg".toMediaTypeOrNull())
    private val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
        name = "photo",
        filename = file.name,
        body = requestImageFile
    )

    //desc body
    private val descString: String = "desc"
    private val desc: RequestBody = descString.toRequestBody("text/plain".toMediaTypeOrNull())

    @Before
    fun setup() {
        createViewModel = StoryCreateViewModel(storyRepository)
    }

    @Test
    fun `post create story not null & return success`() = runTest {
        val expect = Resource.Success(DataDummy.generateDummyCreateStorySuccess())

        `when`(storyRepository.postStory(imageMultiPart, desc)).thenReturn(expect)

        createViewModel.postStory(imageFile = imageMultiPart, desc = desc)

        val actual = createViewModel.responseResult.getOrAwaitValue()

        Mockito.verify(storyRepository).postStory(imageMultiPart, desc)
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Success)
    }

    @Test
    fun `post create story not null & return error`() = runTest {
        val expect = Resource.Error(DataDummy.generateDummyCreateStoryError().message, null)

        `when`(storyRepository.postStory(imageMultiPart, desc)).thenReturn(expect)

        createViewModel.postStory(imageFile = imageMultiPart, desc = desc)

        val actual = createViewModel.responseResult.getOrAwaitValue()

        Mockito.verify(storyRepository).postStory(imageMultiPart, desc)
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Error)
    }
}