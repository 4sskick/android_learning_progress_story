package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesViewModel(
    private val storiesRepository: StoriesRepositoryImpl,
//    private val dispatcher: Dispatcher
) : BaseViewModel() {

    companion object {
        val TAG = StoriesViewModel::class.java.simpleName
    }

    private val storiesResult_ = MutableLiveData<PagingData<Story>>()
    val storiesResult = storiesResult_

////    init {
////        getStories()
////    }
//
//    /*private*/ fun getStories() {
////        storiesResult_.value = Resource.Loading
//        viewModelScope.launch {
//            val result = storiesRepository.getAll_().cachedIn(viewModelScope).asLiveData()
//
//            viewModelScope.launch {
//
//                storiesResult_.postValue(result.value)
//            }
//        }
//    }

    fun getStories(): LiveData<PagingData<Story>> {
        return storiesRepository.getAll_().cachedIn(viewModelScope)
//        viewModelScope.launch {
//
//            val response = storiesRepository.getAll_().cachedIn(this).asLiveData()
//            storiesResult_.postValue(response.value)
//        }
    }
}