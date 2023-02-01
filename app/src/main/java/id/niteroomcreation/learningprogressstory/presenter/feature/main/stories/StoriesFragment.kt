package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.niteroomcreation.learningprogressstory.databinding.FStoriesBinding
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.StoriesViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.detail.StoryDetailActivity
import id.niteroomcreation.learningprogressstory.presenter.listener.ItemViewClickListener
import id.niteroomcreation.learningprogressstory.util.LogHelper

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesFragment : BaseFragment<StoriesViewModel>() {

    companion object {
        val TAG = StoriesFragment::class.java.simpleName
    }

    private lateinit var binding: FStoriesBinding
    private lateinit var adapter: StoriesAdapter


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

        adapter = StoriesAdapter(
            emptyList(), listener = object : ItemViewClickListener<Story> {
                override fun onItemClicked(model: Story) {
                    LogHelper.j(TAG, model)

                    startActivity(Intent(requireContext(), StoryDetailActivity::class.java).also {
                        it.putExtra("data", model)
                    }, ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
                }
            }
        )

        binding.storiesRv.layoutManager = LinearLayoutManager(context)
        binding.storiesRv.adapter = adapter
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, StoriesViewModel::class.java)

        mViewModel.storiesResult.observe(this, Observer {
            LogHelper.j(TAG, it)

            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Error -> {
                    dismissLoading()
                    showMessage(it.message)
                }
                is Resource.Success -> {
                    dismissLoading()
                    adapter.update(it.data.listStory)
                }
            }

        })

    }
}