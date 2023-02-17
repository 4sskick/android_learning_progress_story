package id.niteroomcreation.learningprogressstory.presenter.feature.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.data.repository.LoginRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepositoryImpl,
//    private val dispatcher: Dispatcher
) : BaseViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: MutableLiveData<Resource<LoginResponse>> = _loginResult

    fun login(email: String, password: String) {

        _loginResult.value = Resource.Loading
        viewModelScope.launch {
            val result = loginRepository.login(email, password)
            _loginResult.postValue(result)
        }

    }
}