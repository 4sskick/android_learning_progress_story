package id.niteroomcreation.learningprogressstory.presenter.feature.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.niteroomcreation.learningprogressstory.databinding.FMapBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment

/**
 * Created by Septian Adi Wijaya on 15/02/2023.
 * please be sure to add credential if you use people's code
 */
class MapFragment : BaseFragment<MapViewModel>() {

    private lateinit var binding:FMapBinding

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FMapBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initUI() {
        setupObserver()
    }

    override fun setupObserver() {

    }
}