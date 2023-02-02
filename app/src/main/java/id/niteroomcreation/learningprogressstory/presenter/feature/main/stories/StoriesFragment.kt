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
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.StoriesViewModel
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create.StoryCreateActivity
import id.niteroomcreation.learningprogressstory.util.LogHelper
import id.niteroomcreation.learningprogressstory.util.NavUtil
import id.niteroomcreation.learningprogressstory.util.PrefKey

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

        binding.storiesCreate.setOnClickListener {
            this@StoriesFragment.activity?.let { it1 -> /*NavUtil.gotoStoryCreate(it1)*/startActivity(
                Intent(it1, StoryCreateActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(it1).toBundle()
            )
            }
        }
    }

    private fun setupAdapter() {

        adapter = StoriesAdapter(emptyList())

        binding.storiesRv.layoutManager = LinearLayoutManager(context)
        binding.storiesRv.adapter = adapter
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, StoriesViewModel::class.java)

        mViewModel.storiesResult.observe(this, Observer {
            LogHelper.j(TAG, it, prefUser)

            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Error -> {
                    dismissLoading()

                    if (it.exception?.message != null) {
                        showMessage(it.exception.message)

                        if (!prefApp.getBoolean(PrefKey.LOGIN_FLAG)) {
                            NavUtil.gotoAuth(requireActivity())
                        }
                    } else
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