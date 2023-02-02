package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create

import android.transition.Fade
import android.transition.Slide
import android.view.Window
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AStoryCreateBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity

/**
 * Created by Septian Adi Wijaya on 02/02/2023.
 * please be sure to add credential if you use people's code
 */
class StoryCreateActivity : BaseActivity() {

    companion object {
        val TAG = StoryCreateActivity::class.java.simpleName
    }

    private lateinit var binding: AStoryCreateBinding

    override fun initUI() {
        setupObserver()
    }

    override fun onCreateAnimInside() {

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

            //set exit transition
            enterTransition = Slide().setDuration(500)
            exitTransition = Fade().setDuration(500)
        }
    }

    override fun onCreateInside() {
        binding = AStoryCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun contentLayout(): Int {
        return R.layout.a_story_create
    }

    override fun setupObserver() {

    }
}