package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

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

    private val data_ = MutableLiveData<List<Story>>()
    val data: LiveData<List<Story>> = data_

    private val storiesResult_ = MutableLiveData<Resource<StoriesResponse>>()
    val storiesResult = storiesResult_

    init {
        data()
    }

    fun getStories(){
        storiesResult_.value = Resource.Loading
        viewModelScope.launch(dispatcher.io) {
            val result =
        }
    }

    fun data() {
        data_.value =
            listOf(
                Story(
                    "0",
                    "tian",
                    "desc 0",
                    "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                    "",
                    0f,
                    0f
                ),
                Story(
                    "1",
                    "tian",
                    "desc 1",
                    "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                    "",
                    0f,
                    0f
                ),
                Story(
                    "2",
                    "tian",
                    "desc 2",
                    "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                    "",
                    0f,
                    0f
                ),
                Story(
                    "3",
                    "tian",
                    "desc 3",
                    "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                    "",
                    0f,
                    0f
                ),
                Story(
                    "4",
                    "tian",
                    "desc 4",
                    "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                    "",
                    0f,
                    0f
                ),
            )
    }
}