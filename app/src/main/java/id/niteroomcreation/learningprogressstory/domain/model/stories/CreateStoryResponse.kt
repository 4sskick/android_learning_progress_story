package id.niteroomcreation.learningprogressstory.domain.model.stories

import com.google.gson.annotations.SerializedName

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
data class CreateStoryResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)