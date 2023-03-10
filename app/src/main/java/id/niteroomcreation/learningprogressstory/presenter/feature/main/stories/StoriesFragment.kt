package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.FStoriesBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create.StoryCreateActivity
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.paging_footer.StoriesFooterAdapter
import id.niteroomcreation.learningprogressstory.util.LogHelper
import kotlinx.coroutines.launch

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesFragment : BaseFragment<StoriesViewModel>() {

    companion object {
        val TAG = StoriesFragment::class.java.simpleName
    }

    private lateinit var binding: FStoriesBinding
    private lateinit var adapter: StoriesAdapterPaging


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
        setupAdapter()

        binding.storiesCreate.setOnClickListener {
            this@StoriesFragment.activity?.let { it1 -> /*NavUtil.gotoStoryCreate(it1)*/startActivity(
                Intent(it1, StoryCreateActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(it1).toBundle()
            )
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.addLoadStateListener { cLoadState: CombinedLoadStates ->
                if (cLoadState.refresh is LoadState.Loading)
                    showLoading()
                else if (cLoadState.refresh is LoadState.Error) {
                    showLoading()
                    showMessage(R.string.info_failed_request)
                    dismissLoading()

                    if (adapter.itemCount == 0)
                        showEmptyState()
                } else if (cLoadState.refresh is LoadState.NotLoading)
                    dismissLoading()
            }

            mViewModel.getStories().observe(viewLifecycleOwner, Observer {

                LogHelper.j(TAG, it)

                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        }
    }

    private fun setupAdapter() {

        adapter = StoriesAdapterPaging()

        binding.storiesRv.layoutManager = LinearLayoutManager(context)
        binding.storiesRv.adapter = adapter.withLoadStateFooter(StoriesFooterAdapter())


    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, StoriesViewModel::class.java)

//        mViewModel.storiesResult.observe(this, Observer {
//            when (it) {
//                is Resource.Loading -> showLoading()
//                is Resource.Error -> {
//                    dismissLoading()
//
//                    if (it.exception?.message != null) {
//                        showMessage(it.exception.message)
//
//                        if (!prefApp.getBoolean(PrefKey.LOGIN_FLAG)) {
//                            NavUtil.gotoAuth(requireActivity())
//                        }
//                    } else
//                        showMessage(it.message)
//                }
//                is Resource.Success -> {
//                    dismissLoading()
//
//                    if (it.data.listStory.isEmpty())
//                        showMessage("Data kosong tidak ditemukan")
//                    else
//                        adapter.update(it.data.listStory)
//                }
//            }
//
//        })

    }
}