package id.niteroomcreation.learningprogressstory.presenter.feature.auth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.data.repository.LoginRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import id.niteroomcreation.learningprogressstory.util.PrefUtil
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepositoryImpl,
    private val dispatcher: Dispatcher
) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    //    private val _loginResult = MutableLiveData<LoginResult>()
//    val loginResult: LiveData<LoginResult> = _loginResult

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: LiveData<Resource<LoginResponse>> = _loginResult

    fun login(email: String, password: String) {

        _loginResult.value = Resource.Loading
        viewModelScope.launch(dispatcher.io) {
            val result = loginRepository.login(email, password)
            _loginResult.postValue(result)
        }

    }

//    fun loginDataChanged(username: String, password: String) {
//        if (!isUserNameValid(username)) {
//            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
//        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//        } else {
//            _loginForm.value = LoginFormState(isDataValid = true)
//        }
//    }
//
//    // A placeholder username validation check
//    private fun isUserNameValid(username: String): Boolean {
//        return if (username.contains('@')) {
//            Patterns.EMAIL_ADDRESS.matcher(username).matches()
//        } else {
//            username.isNotBlank()
//        }
//    }
//
//    // A placeholder password validation check
//    private fun isPasswordValid(password: String): Boolean {
//        return password.length > 5
//    }
}