package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.detail

import android.transition.Fade
import android.transition.Slide
import android.view.Window
import com.bumptech.glide.Glide
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AStoryDetailBinding
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity
import id.niteroomcreation.learningprogressstory.util.dateFormatted

/**
 * Created by Septian Adi Wijaya on 31/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoryDetailActivity : BaseActivity() {

    companion object {
        val TAG = StoryDetailActivity::class.java.simpleName
    }

    private lateinit var binding: AStoryDetailBinding

    private val data: Story? by lazy {
        intent.getParcelableExtra<Story>("data")
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
        binding = AStoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun contentLayout(): Int {
        return R.layout.a_story_detail
    }

    override fun initUI() {
        if (data != null) {
            Glide.with(this)
                .load(data!!.photoUrl)
                .fitCenter()
                .into(binding.detailImage)

            binding.detailDescVal.setText(data!!.description)
            binding.detailPostedByVal.setText(data!!.name)
            binding.detailDateVal.setText(data!!.createdAt.dateFormatted())
        } else
            finish()
    }
}