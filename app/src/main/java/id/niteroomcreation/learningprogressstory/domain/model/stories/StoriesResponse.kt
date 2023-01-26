package id.niteroomcreation.learningprogressstory.domain.model.stories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */

@Parcelize
data class StoriesResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("listStory")
    val listStory: ArrayList<Story>
) : Parcelable

@Parcelize
data class Story(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("photoUrl")
    val photoUrl: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("lat")
    val lat: Float,

    @field:SerializedName("lon")
    val lon: Float,
) : Parcelable