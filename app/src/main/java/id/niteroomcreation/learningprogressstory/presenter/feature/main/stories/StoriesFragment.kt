package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.niteroomcreation.learningprogressstory.databinding.FStoriesBinding
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesFragment : BaseFragment<StoriesViewModel>() {

    companion object {
        val TAG = StoriesFragment::class.java.simpleName
    }

    private lateinit var binding: FStoriesBinding

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FStoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initUI() {
        setupObserver()
        setupAdapter();
    }

    private fun setupAdapter() {

        val data = listOf(
            Story(
                "0",
                "tian",
                "desc 0",
                "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                "",
                0f,
                0f
            ),
            Story(
                "1",
                "tian",
                "desc 1",
                "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                "",
                0f,
                0f
            ),
            Story(
                "2",
                "tian",
                "desc 2",
                "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                "",
                0f,
                0f
            ),
            Story(
                "3",
                "tian",
                "desc 3",
                "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                "",
                0f,
                0f
            ),
            Story(
                "4",
                "tian",
                "desc 4",
                "https://pbs.twimg.com/media/FnXzKXmagAEgbjL?format=jpg",
                "",
                0f,
                0f
            ),
        )

        val adapter = StoriesAdapter(data)

        binding.storiesRv.layoutManager = LinearLayoutManager(context)
        binding.storiesRv.adapter = adapter
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, StoriesViewModel::class.java)
    }
}