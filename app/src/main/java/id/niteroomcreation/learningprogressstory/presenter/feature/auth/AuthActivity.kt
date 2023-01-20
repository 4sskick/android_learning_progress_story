package id.niteroomcreation.learningprogressstory.presenter.feature.auth

import androidx.fragment.app.FragmentManager
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AAuthBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.register.RegisterFragment
import id.niteroomcreation.learningprogressstory.util.LogHelper
import id.niteroomcreation.learningprogressstory.util.NavUtil

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class AuthActivity : BaseActivity() {

    companion object {
        val TAG = AuthActivity::class.java.simpleName
    }

    private lateinit var binding: AAuthBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreateInside() {
        binding = AAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun contentLayout(): Int {
        return R.layout.a_auth
    }

    override fun initUI() {
        LogHelper.e(TAG, "inside auth act")

        fragmentManager = supportFragmentManager

        NavUtil.moveToFragment(
            fragmentManager,
            binding.flAuth.id,
            RegisterFragment.newInstance(),
            RegisterFragment.TAG,
            false
        )
    }


}