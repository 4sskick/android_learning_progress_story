package id.niteroomcreation.learningprogressstory.presenter.feature.main

import androidx.fragment.app.FragmentManager
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AMainBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class MainActivity : BaseActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: AMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreateInside() {
        binding = AMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun contentLayout(): Int {
        return R.layout.a_main
    }

    override fun initUI() {
        fragmentManager = supportFragmentManager

    }
}