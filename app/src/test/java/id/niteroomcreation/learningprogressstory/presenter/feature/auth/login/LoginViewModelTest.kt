package id.niteroomcreation.learningprogressstory.presenter.feature.auth.login

import id.niteroomcreation.learningprogressstory.data.repository.LoginRepository
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Septian Adi Wijaya on 17/02/2023.
 * please be sure to add credential if you use people's code
 */

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {


    private val email = "email@gmail.com"
    private val pass = "123123123"

    @Mock
    private lateinit var repository: LoginRepository
    @Mock
    private lateinit var dispatcher: Dispatcher
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(repository, dispatcher)
    }

    @Test
    fun `username tidak valid`() = runBlocking{
//        Assert.assertEquals(1, 1)

    }
}