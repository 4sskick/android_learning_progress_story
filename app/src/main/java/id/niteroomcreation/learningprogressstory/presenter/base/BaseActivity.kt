package id.niteroomcreation.learningprogressstory.presenter.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.BActivityBinding
import id.niteroomcreation.learningprogressstory.presenter.custom.CLoadingDialog

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    companion object {
        val TAG: String = BaseActivity::class.java.simpleName
        const val EMPTY_LAYOUT: Int = 0
    }

    private lateinit var toast: Toast
    private lateinit var progressLoading: Dialog
    private lateinit var context: Context
    private lateinit var binding: BActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        progressLoading = CLoadingDialog.progressDialog(context)
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        onCreateInside()
        initUI()

        if (contentLayout() != EMPTY_LAYOUT) {
            try {

                var inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                inflater.inflate(contentLayout(), binding.layoutContent)

            } catch (e: Exception) {
                throw RuntimeException("Inflating contentLayout() failed on ${this.javaClass.simpleName}")
            }
        }

    }

    fun getActContext(): Context {
        return context
    }

    override fun showLoading(title: String?, desc: String?) {
        progressLoading.setTitle(if (title == null) title else this.resources.getString(R.string.info_loading_title))
        progressLoading.setCancelable(false)
        progressLoading.setCanceledOnTouchOutside(false)

        if (!progressLoading.isShowing) {
            runOnUiThread { progressLoading.show() }
        }
    }

    override fun showLoading() {
        showLoading(null, null)
    }

    override fun dismissLoading() {
        if (progressLoading.isShowing) {
            runOnUiThread { progressLoading.dismiss() }
        }
    }

    override fun showMessage(message: String?) {
        toast.cancel()

        if (!message.isNullOrEmpty())
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT).also { it.show() }
    }

    override fun showMessage(resId: Int) {
        showMessage(this.getString(resId))
    }
}