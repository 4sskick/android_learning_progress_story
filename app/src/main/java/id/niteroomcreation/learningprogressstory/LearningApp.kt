package id.niteroomcreation.learningprogressstory

import android.app.Application
import android.content.Context

/**
 * Created by Septian Adi Wijaya on 30/01/2023.
 * please be sure to add credential if you use people's code
 */
class LearningApp : Application() {

    companion object {
        private lateinit var context: Context

        fun getContext() = context
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }
}