package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser

/**
 * Created by Septian Adi Wijaya on 01/02/2023.
 * please be sure to add credential if you use people's code
 */
interface ProfileRepositoryImpl {

    suspend fun profileData():Resource<LoggedInUser>
}