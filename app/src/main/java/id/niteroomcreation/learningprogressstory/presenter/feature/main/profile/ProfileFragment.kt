package id.niteroomcreation.learningprogressstory.presenter.feature.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.niteroomcreation.learningprogressstory.databinding.FProfileBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment

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

    }


}