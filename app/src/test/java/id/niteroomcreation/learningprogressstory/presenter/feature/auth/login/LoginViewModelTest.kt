package id.niteroomcreation.learningprogressstory.presenter.feature.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.niteroomcreation.learningprogressstory.data.repository.LoginRepository
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.util.DataDummy
import id.niteroomcreation.learningprogressstory.util.MainCoroutineRule
import id.niteroomcreation.learningprogressstory.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
 * Created by Septian Adi Wijaya on 17/02/2023.
 * please be sure to add credential if you use people's code
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {


    private val email = ""
    private val pass = "123123123"

    @Mock
    private lateinit var repository: LoginRepository
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcher = MainCoroutineRule()

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(repository)
    }

    @Test
    fun `login not null and return success`() = runTest {

        val expect = Resource.Success(DataDummy.generateDummyLoginSuccess())

        //set return value to what we have already set
        `when`(repository.login(username = email, password = pass)).thenReturn(expect)

        //call the function
        loginViewModel.login(email = email, password = pass)
        //get the value which assigned
        val actual = loginViewModel.loginResult.getOrAwaitValue()

        //verify the function 'login' already called or not
        Mockito.verify(repository).login(email, pass)
        //make sure not null
        Assert.assertNotNull(actual)
        //and value is equal
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Success)
    }

    @Test
    fun `login not null and return error`() = runTest {

        val expect = Resource.Error(DataDummy.generateDummyLoginError().message, null)

        //set return value to what we have already set
        `when`(repository.login(username = email, password = pass)).thenReturn(expect)

        //call the function
        loginViewModel.login(email = email, password = pass)
        //get the value which assigned
        val actual = loginViewModel.loginResult.getOrAwaitValue()

        //verify the function 'login' already called or not
        Mockito.verify(repository).login(email, pass)
        Assert.assertNotNull(actual)
        Assert.assertEquals(expect, actual)
        Assert.assertTrue(actual is Resource.Error)
    }
}