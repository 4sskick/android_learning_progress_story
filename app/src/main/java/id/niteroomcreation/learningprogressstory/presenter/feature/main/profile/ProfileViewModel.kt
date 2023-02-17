package id.niteroomcreation.learningprogressstory.presenter.feature.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.data.repository.ProfileRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */
class ProfileViewModel(
    private val profileRepository: ProfileRepositoryImpl,
//    private val dispatcher: Dispatcher
) : BaseViewModel() {

    companion object {
        val TAG = ProfileViewModel::class.java.simpleName
    }

    private val profileResult_ = MutableLiveData<Resource<LoggedInUser>>()
    val profileResult = profileResult_

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        profileResult_.value = Resource.Loading
        viewModelScope.launch {
            val result = profileRepository.profileData()
            profileResult_.postValue(result)
        }

    }
}