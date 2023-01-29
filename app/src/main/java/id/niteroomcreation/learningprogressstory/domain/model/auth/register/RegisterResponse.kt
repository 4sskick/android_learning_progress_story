package id.niteroomcreation.learningprogressstory.domain.model.auth.register

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
@Parcelize
data class RegisterResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
) : Parcelable