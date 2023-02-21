package id.niteroomcreation.learningprogressstory.presenter.feature.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.FMapBinding
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse
import id.niteroomcreation.learningprogressstory.presenter.base.BaseFragment
import id.niteroomcreation.learningprogressstory.util.Constants
import id.niteroomcreation.learningprogressstory.util.LogHelper

/**
 * Created by Septian Adi Wijaya on 15/02/2023.
 * please be sure to add credential if you use people's code
 */
class MapFragment : BaseFragment<MapViewModel>(), OnMapReadyCallback {

    private lateinit var binding: FMapBinding

    //    private lateinit var mMap: GoogleMap
    private val boundsBuilder = LatLngBounds.Builder()

    override fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FMapBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initUI() {

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        setupObserver()
    }

    override fun setupObserver() {
        mViewModel = obtainViewModel(this, MapViewModel::class.java)

    }

    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap

        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true

        val dummyLocation = LatLng(Constants.DICODING_LATITUDE, Constants.DICODING_LONGITUDE)
        googleMap.addMarker(
            MarkerOptions()
                .position(dummyLocation)
                .title("Marker in dummyLocation")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dummyLocation, 5f))


        setupStyleMap(googleMap)
        setupLocationStoryMap(googleMap)
    }

    private fun setupMarker(data: StoriesResponse, googleMap: GoogleMap) {
        data.listStory.forEach { story ->
            val latLng = LatLng(story.lat.toDouble(), story.lon.toDouble())
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(story.createdAt)
                    .snippet(
                        StringBuilder("created: ")
                            .append(story.createdAt.subSequence(11, 16).toString())
                            .toString()
                    )
            )
            boundsBuilder.include(latLng)
        }
    }

    private fun setupStyleMap(googleMap: GoogleMap) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.map_style
                )
            )

            if (!success)
                showMessage("Error set style from json")
//            mMap.mapType = GoogleMap
        } catch (e: Exception) {
            showMessage("Error")
        }
    }

    private fun setupLocationStoryMap(googleMap: GoogleMap) {
        mViewModel.getStoriesWithLocation()
        mViewModel.storiesResult.observe(this, Observer {
            when (it) {
                is Resource.Error -> {
                    dismissLoading()
                    showMessage(it.message)
                }
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    dismissLoading()
                    LogHelper.j(TAG, it)

                    setupMarker(it.data, googleMap)
                }
            }
        })
    }
}