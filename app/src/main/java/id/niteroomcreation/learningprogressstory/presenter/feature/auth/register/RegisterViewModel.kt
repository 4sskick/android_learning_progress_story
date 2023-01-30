package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.data.repository.RegisterRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import id.niteroomcreation.learningprogressstory.util.LogHelper
import kotlinx.coroutines.launch

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class RegisterViewModel(
    private val registerRepository: RegisterRepositoryImpl,
    private val dispatcher: Dispatcher
) : BaseViewModel() {

    companion object {
        val TAG = RegisterViewModel::class.java.simpleName
    }

    //    private val _registerResult = MutableLiveData<RegisterResult>()
    private val _registerResult = MutableLiveData<Resource<RegisterResponse>>()
    val registerResult = _registerResult

    fun register(name: String, email: String, password: String, passwordConfirm: String) {

        _registerResult.value = Resource.Loading

        viewModelScope.launch(dispatcher.io) {
            val result = registerRepository.register(name, email, password, passwordConfirm)

            LogHelper.j(TAG, result)

            _registerResult.postValue(result)
        }
    }
}