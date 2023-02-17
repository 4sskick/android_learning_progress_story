package id.niteroomcreation.learningprogressstory.presenter.feature.main.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Septian Adi Wijaya on 15/02/2023.
 * please be sure to add credential if you use people's code
 */
class MapViewModel(
    private val storiesRepository: StoriesRepositoryImpl,
//    private val dispatcher: Dispatcher
) : BaseViewModel() {

    companion object {
        val TAG = MapViewModel.javaClass.simpleName
    }

    private val storiesResult_ = MutableLiveData<Resource<StoriesResponse>>()
    val storiesResult = storiesResult_

    fun getStoriesWithLocation() {
        storiesResult_.value = Resource.Loading
        viewModelScope.launch {
            val result = storiesRepository.getAllWithLocation()
            storiesResult_.postValue(result)
        }
    }
}