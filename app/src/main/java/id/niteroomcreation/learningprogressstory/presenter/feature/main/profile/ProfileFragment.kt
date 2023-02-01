package id.niteroomcreation.learningprogressstory.presenter.feature.main.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import id.niteroomcreation.learningprogressstory.databinding.FProfileBinding
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.util.NavUtil
import id.niteroomcreation.learningprogressstory.util.PrefKey

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */
class ProfileFragment : BaseFragment<ProfileViewModel>() {

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
    }

    private lateinit var binding: FProfileBinding

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initUI() {
        setupObserver()

        binding.profileLayoutLogout.setOnClickListener {
            prefUser.deleteByPref(PrefKey.LOGIN_TOKEN)
            prefApp.setBoolean(PrefKey.LOGIN_FLAG, false)

            NavUtil.gotoAuth(context as Activity)
        }

        binding.profileLayoutLanguage.setOnClickListener {
            startActivity(
                Intent(
                    Settings.ACTION_LOCALE_SETTINGS
                )
            )
        }
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, ProfileViewModel::class.java)
        mViewModel.profileResult.observe(this, Observer {
            when (it) {
                is Resource.Error -> {
                    dismissLoading()
                    showMessage(it.message)
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    dismissLoading()
                    binding.profileUsername.text = it.data.displayName
                    binding.profileUserId.text = it.data.userId

                }
            }
        })
    }
}