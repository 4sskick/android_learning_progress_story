package id.niteroomcreation.learningprogressstory.presenter.feature.auth

import androidx.fragment.app.FragmentManager
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AAuthBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.login.LoginFragment
import id.niteroomcreation.learningprogressstory.presenter.feature.auth.register.RegisterFragment
import id.niteroomcreation.learningprogressstory.util.NavUtil
import id.niteroomcreation.learningprogressstory.util.PrefKey

/**
 * Created by Septian Adi Wijaya on 20/01/2023.
 * please be sure to add credential if you use people's code
 */
class AuthActivity : BaseActivity(),
    LoginFragment.LoginInterface,
    RegisterFragment.RegisterInterface {

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
        fragmentManager = supportFragmentManager

        if (prefApp.getBoolean(PrefKey.LOGIN_FLAG))
            NavUtil.gotoMain(this)
        else
            NavUtil.moveToFragment(
                fragmentManager,
                binding.flAuth.id,
                LoginFragment.newInstance(),
                LoginFragment.TAG,

                false
            )
    }

    override fun onGotoRegister() {

        NavUtil.moveToFragment(
            fragmentManager,
            binding.flAuth.id,
            RegisterFragment.newInstance(),
            RegisterFragment.TAG,
            false
        )
    }

    override fun onGotoLogin() {

        NavUtil.moveToFragment(
            fragmentManager,
            binding.flAuth.id,
            LoginFragment.newInstance(),
            LoginFragment.TAG,
            false
        )
    }

    override fun onRegisterOperation() {
        NavUtil.gotoMain(this)
    }

    override fun onLoginOperation() {
        NavUtil.gotoMain(this)
    }
}

interface AuthInterface {
    fun onGotoRegister()
    fun onGotoLogin()
}
