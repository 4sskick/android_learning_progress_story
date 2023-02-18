package id.niteroomcreation.learningprogressstory.util

import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResponse
import id.niteroomcreation.learningprogressstory.domain.model.auth.login.LoginResultResponse
import id.niteroomcreation.learningprogressstory.domain.model.auth.register.RegisterResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.CreateStoryResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story

/**
 * Created by Septian Adi Wijaya on 18/02/2023.
 * please be sure to add credential if you use people's code
 */
object DataDummy {
    fun generateDummyLoginSuccess(): LoginResponse {
        return LoginResponse(
            error = false,
            message = "success",
            loginResult = LoginResultResponse(
                userId = "userId",
                name = "name",
                token = "token"
            )
        )
    }

    fun generateDummyLoginError(): LoginResponse {
        return LoginResponse(
            error = true,
            message = "invalid password"
        )
    }

    fun generateDummyRegisterSuccess(): RegisterResponse {
        return RegisterResponse(
            error = false,
            message = "success"
        )
    }

    fun generateDummyRegisterError(): RegisterResponse {
        return RegisterResponse(
            error = true,
            message = "bad request"
        )
    }

    fun generateDummyCreateStorySuccess(): CreateStoryResponse {
        return CreateStoryResponse(
            error = false,
            message = "success"
        )
    }

    fun generateDummyCreateStoryError(): CreateStoryResponse {
        return CreateStoryResponse(
            error = true,
            message = "error"
        )
    }

    fun generateDummyStory(): StoriesResponse {
        return StoriesResponse(
            error = false,
            message = "success",
            listStory = arrayListOf(
                Story(
                    id = "id",
                    name = "name",
                    description = "description",
                    photoUrl = "photoUrl",
                    createdAt = "createdAt",
                    lat = 0.01f,
                    lon = 0.01f
                )
            )
        )
    }

    fun generateErrorDummyStory(): StoriesResponse {
        return StoriesResponse(
            error = true,
            message = "error",
            listStory = arrayListOf(
                Story(
                    id = "id",
                    name = "name",
                    description = "description",
                    photoUrl = "photoUrl",
                    createdAt = "createdAt",
                    lat = 0.01f,
                    lon = 0.01f
                )
            )
        )
    }
}

