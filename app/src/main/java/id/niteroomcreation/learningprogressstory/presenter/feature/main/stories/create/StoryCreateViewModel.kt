package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.niteroomcreation.learningprogressstory.data.repository.StoriesRepositoryImpl
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.service.Dispatcher
import id.niteroomcreation.learningprogressstory.presenter.base.BaseViewModel
import id.niteroomcreation.learningprogressstory.util.reduceFileImage
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

/**
 * Created by Septian Adi Wijaya on 02/02/2023.
 * please be sure to add credential if you use people's code
 */
class StoryCreateViewModel(
    private val storiesRepository: StoriesRepositoryImpl,
//    private val dispatcher: Dispatcher
) : BaseViewModel() {

    companion object {
        val TAG = StoryCreateViewModel::class.java.simpleName
    }

    private val responseResult_ = MutableLiveData<Resource<CreateStoryResponse>>()
    val responseResult = responseResult_

    fun prepareBody(desc:String):RequestBody{
        return desc.toRequestBody("text/plain".toMediaType())
    }

    fun prepareBodyImage(imageFile: File):MultipartBody.Part{

//        processing image to reduce size file
            val fileReduced = reduceFileImage(imageFile)

//        prepare for body

            val file = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData(
                    "photo",
                    imageFile.name,
                    file
                )
    }

//    fun postStory(imageFile: File, desc: String) {
    fun postStory(imageFile: MultipartBody.Part, desc: RequestBody) {

        responseResult_.value = Resource.Loading
        viewModelScope.launch {

            val result = storiesRepository.postStory(imageFile, desc)
            responseResult_.postValue(result)
        }
    }
}