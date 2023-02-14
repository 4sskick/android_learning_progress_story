package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesViewModel(
    private val storiesRepository: StoriesRepositoryImpl,
    private val dispatcher: Dispatcher
) : BaseViewModel() {

    companion object {
        val TAG = StoriesViewModel::class.java.simpleName
    }

    private val storiesResult_ = MutableLiveData<Resource<StoriesResponse>>()
    val storiesResult = storiesResult_

//    init {
//        getStories()
//    }
//
//    private fun getStories() {
//        storiesResult_.value = Resource.Loading
//        viewModelScope.launch(dispatcher.io) {
//            val result = storiesRepository.getAll()
//            storiesResult_.postValue(result)
//        }
//    }

    fun getStories():Flow<PagingData<Story>>{
         return storiesRepository.getAll_().cachedIn(viewModelScope)
    }
}