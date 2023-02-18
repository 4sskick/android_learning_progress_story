package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.niteroomcreation.learningprogressstory.data.repository.RegisterRepository
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.util.DataDummy
import id.niteroomcreation.learningprogressstory.util.MainCoroutineRule
import id.niteroomcreation.learningprogressstory.util.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.Assert
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
class RegisterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcher = MainCoroutineRule()

    @Mock
    private lateinit var registerRepository: RegisterRepository
    private lateinit var registerViewModel: RegisterViewModel

    @Before
    fun setup() {
        registerViewModel = RegisterViewModel(registerRepository)
    }

    @Test
    fun `register not null & success`() = runTest {
        val expect = Resource.Success(DataDummy.generateDummyRegisterSuccess())

        val pass = "123123123"
        val passConfirm = "321_321_321"

        `when`(
            registerRepository.register(
                name = "String",
                email = "String@mail.com",
                password = pass,
                passwordConfirm = passConfirm
            )
        ).thenReturn(expect)
        registerViewModel.register(
            name = "String",
            email = "String@mail.com",
            password = pass,
            passwordConfirm = passConfirm
        )

        val actual = registerViewModel.registerResult.getOrAwaitValue()

        Mockito.verify(registerRepository)
            .register(
                name = "String",
                email = "String@mail.com",
                password = pass,
                passwordConfirm = passConfirm
            )
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Success)
    }

    @Test
    fun `register not null & error password not same`() = runTest {
        val expect = Resource.Error(DataDummy.generateDummyRegisterError().message, null)

        val pass = "123123123"
        val passConfirm = "321_321_321"

        `when`(
            registerRepository.register(
                name = "String",
                email = "String@mail.com",
                password = pass,
                passwordConfirm = passConfirm
            )
        ).thenReturn(expect)
        registerViewModel.register(
            name = "String",
            email = "String@mail.com",
            password = pass,
            passwordConfirm = passConfirm
        )

        val actual = registerViewModel.registerResult.getOrAwaitValue()

        Mockito.verify(registerRepository)
            .register(
                name = "String",
                email = "String@mail.com",
                password = pass,
                passwordConfirm = passConfirm
            )
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Error)
    }
}