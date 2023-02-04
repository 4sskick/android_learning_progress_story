package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.create

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AStoryCreateBinding
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity
import id.niteroomcreation.learningprogressstory.util.*
import java.io.File

/**
 * Created by Septian Adi Wijaya on 02/02/2023.
 * please be sure to add credential if you use people's code
 */
class StoryCreateActivity : BaseActivity() {

    companion object {
        val TAG = StoryCreateActivity::class.java.simpleName
        val call_gallery = "gallery"
        val call_camera = "camera"
    }

    private lateinit var modeCall: String
    private lateinit var binding: AStoryCreateBinding

    private var currentSelectedImage: File? = null
    private lateinit var currentSelectedImageCamera: String
    private lateinit var mViewModel: StoryCreateViewModel

    override fun initUI() {
        setupObserver()

        binding.storyCreatePickGalery.setOnClickListener {
            checkPermission(call_gallery)
        }
        binding.storyCreatePickCamera.setOnClickListener {
            checkPermission(call_camera)
        }

        binding.storyCreateDescEdit.afterTextChanged {
            binding.storyCreateDesc.error =
                if (binding.storyCreateDescEdit.text.toString()
                        .isNotEmpty()
                ) null else getString(R.string.info_cant_empty)
        }

        binding.storyCreateButton.setOnClickListener {

            if (currentSelectedImage == null) {
                showMessage(getString(R.string.info_image_cant_empty))
                return@setOnClickListener
            }

            if (binding.storyCreateDescEdit.text.toString().isEmpty()) {
                binding.storyCreateDesc.error = getString(R.string.info_cant_empty)
                return@setOnClickListener
            }

            mViewModel.postStory(
                imageFile = currentSelectedImage!!,
                desc = binding.storyCreateDescEdit.text.toString()
            )
        }
    }

    override fun onCreateAnimInside() {

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

            //set exit transition
            enterTransition = Slide().setDuration(500)
            exitTransition = Fade().setDuration(500)
        }
    }

    override fun onCreateInside() {
        binding = AStoryCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun contentLayout(): Int {
        return R.layout.a_story_create
    }

    override fun setupObserver() {
        mViewModel = ViewModelProvider(
            owner = this,
            factory = ViewModelFactory.getInstance(this)
        ).get(StoryCreateViewModel::class.java)

        mViewModel.responseResult.observe(this, Observer {
            when (it) {
                is Resource.Error -> {
                    dismissLoading()

                    if (it.exception?.message != null)
                        showMessage(it.exception.message)
                    else
                        showMessage(it.message)
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    dismissLoading()
                    showMessage(getString(R.string.info_success_post_story))
                    onBackPressed()
                }
            }
        })
    }

    override fun onBackPressed() {
        NavUtil.gotoMain(this)
    }

    private fun checkPermission(modeCall: String) {
        this.modeCall = modeCall

        if (!isAllPermissionGranted())
            ActivityCompat.requestPermissions(
                this@StoryCreateActivity,
                Constants.REQUIRED_PERMISSIONS,
                Constants.REQUIRED_PERMISSIONS_REQUEST_CODE
            )
        else
            doPickOperation()
    }

    private fun doPickOperation() {
        if (modeCall.equals(call_gallery)) {
            pickFromGallery()
        } else {
            pickFromCamera()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUIRED_PERMISSIONS_REQUEST_CODE)
            if (!isAllPermissionGranted())
                showMessage(getString(R.string.lbl_permission_denied))
            else doPickOperation()
    }

    private fun pickFromGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"

        val chooser = Intent.createChooser(intent, getString(R.string.lbl_choose_image))
        launcherPickGallery.launch(chooser)
    }

    private fun pickFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        storeTempFile(this@StoryCreateActivity).also {
            val photoUri: Uri = FileProvider.getUriForFile(
                this@StoryCreateActivity,
                "id.niteroomcreation.learningprogressstory",
                it
            )

            currentSelectedImageCamera = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

            launcherPickCamera.launch(intent)
        }
    }

    private val launcherPickCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            currentSelectedImage = File(currentSelectedImageCamera)
            val result = BitmapFactory.decodeFile(currentSelectedImage!!.path)

            binding.storyCreateImage.setImageBitmap(result)
        }
    }

    private val launcherPickGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            currentSelectedImage = uriToFile(selectedImg, this@StoryCreateActivity)

            binding.storyCreateImage.setImageURI(selectedImg)
        }
    }

    //collect all array values
    //then iterate each by picked it out
    private fun isAllPermissionGranted() = Constants.REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this@StoryCreateActivity,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}