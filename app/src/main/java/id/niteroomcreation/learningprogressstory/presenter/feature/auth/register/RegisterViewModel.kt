package id.niteroomcreation.learningprogressstory.presenter.feature.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.data.repository.RegisterRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class RegisterViewModel(
    private val registerRepository: RegisterRepositoryImpl,
    private val dispatcher: Dispatcher
) : BaseViewModel() {

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult = _registerResult

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(dispatcher.io) {
            val result = registerRepository.register(name, email, password)

            if (result is Resource.Success)
                _registerResult.postValue(RegisterResult(success = result.data))
            else
                _registerResult.postValue(RegisterResult(error = R.string.login_failed))
        }
    }

}