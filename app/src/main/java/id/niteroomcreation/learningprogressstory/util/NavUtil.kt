package id.niteroomcreation.learningprogressstory.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.AuthActivity
import id.niteroomcreation.learningprogressstory.presenter.feature.main.MainActivity
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.detail.StoryDetailActivity

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
object NavUtil {

    fun gotoAuth(act: Activity) {
        intentGoto(act, AuthActivity::class.java, true)
    }

    fun gotoMain(act: Activity) {
        intentGoto(act, MainActivity::class.java, true)
    }

    fun gotoStoryDetail(act: Activity, b: Bundle) {
        intentGoto(act, StoryDetailActivity::class.java, b, false)
    }

    fun moveToFragment(
        fragmentManager: FragmentManager,
        viewIdFrameLayout: Int,
        fragment: Fragment?,
        fragmentTag: String?,
        add: Boolean
    ) {
        try {
            if (add) {
                fragmentManager.beginTransaction()
                    .add(viewIdFrameLayout, fragment!!, fragmentTag)
                    .addToBackStack(null)
                    .commit()
            } else {
                fragmentManager.beginTransaction()
                    .replace(viewIdFrameLayout, fragment!!, fragmentTag)
                    .commit()
            }
        } catch (e: Exception) {
            throw IllegalStateException(
                String.format(
                    "Seems like fragmentManager isn't " +
                            "initialized %s", e.message
                )
            )
        }
    }

    fun getFragmentById(
        fragmentManager: FragmentManager,
        viewIdFrameLayout: Int
    ): Fragment? {
        var f: Fragment? = null
        try {
            f = fragmentManager.findFragmentById(viewIdFrameLayout)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return f
    }

    private fun intentGoto(
        act: Activity,
        destination: Class<*>,
        finished: Boolean
    ) {
        val i = Intent(act, destination)
        act.startActivity(i)
        if (finished) act.finish()
    }

    private fun intentGoto(
        act: Activity,
        destination: Class<*>,
        bundle: Bundle,
        finished: Boolean
    ) {
        val i = Intent(act, destination)
        i.putExtras(bundle)
        act.startActivity(i)
        if (finished) act.finish()
    }

}