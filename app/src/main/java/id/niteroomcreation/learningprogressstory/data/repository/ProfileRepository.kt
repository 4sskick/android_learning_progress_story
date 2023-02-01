package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoggedInUser
import id.niteroomcreation.learningprogressstory.util.PrefKey

/**
 * Created by Septian Adi Wijaya on 01/02/2023.
 * please be sure to add credential if you use people's code
 */
class ProfileRepository : ProfileRepositoryImpl {

    override suspend fun profileData(): Resource<LoggedInUser> {
        val prefUser = Injection.providePrefUser()

        val username = prefUser.getString(PrefKey.LOGIN_NAME, "")
        val userId = prefUser.getString(PrefKey.LOGIN_USERID, "")

        if (username!!.isEmpty() || userId!!.isEmpty())
            return Resource.Error("Data user tidak valid", null)

        return Resource.Success(LoggedInUser(userId = userId, displayName = username))
    }
}