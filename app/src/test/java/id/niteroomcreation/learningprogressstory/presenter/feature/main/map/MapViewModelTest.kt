package id.niteroomcreation.learningprogressstory.presenter.feature.main.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepository
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.util.DataDummy
import id.niteroomcreation.learningprogressstory.util.MainCoroutineRule
import id.niteroomcreation.learningprogressstory.util.getOrAwaitValue
import kotlinx.coroutines.test.runTest
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

/**
 * Created by Septian Adi Wijaya on 18/02/2023.
 * please be sure to add credential if you use people's code
 */

@RunWith(MockitoJUnitRunner::class)
class MapViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcher = MainCoroutineRule()

    @Mock
    private lateinit var storyRepository: StoriesRepository
    private lateinit var mapViewModel: MapViewModel

    @Before
    fun setup() {
        mapViewModel = MapViewModel(storyRepository)
    }

    @Test
    fun `get story not null & success`() = runTest {
        val expect = Resource.Success(DataDummy.generateDummyStory())

        `when`(storyRepository.getAllWithLocation()).thenReturn(expect)
        mapViewModel.getStoriesWithLocation()

        val actual = mapViewModel.storiesResult.getOrAwaitValue()

        Mockito.verify(storyRepository).getAllWithLocation()
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Success)
    }

    @Test
    fun `get story not null & error`() = runTest {
        val expect = Resource.Error(DataDummy.generateErrorDummyStory().message, null)

        `when`(storyRepository.getAllWithLocation()).thenReturn(expect)
        mapViewModel.getStoriesWithLocation()

        val actual = mapViewModel.storiesResult.getOrAwaitValue()

        Mockito.verify(storyRepository).getAllWithLocation()
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Error)
    }
}