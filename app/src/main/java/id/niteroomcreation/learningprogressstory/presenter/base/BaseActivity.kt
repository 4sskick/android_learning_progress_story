package id.niteroomcreation.learningprogressstory.presenter.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.BActivityBinding
import id.niteroomcreation.learningprogressstory.domain.di.Injection
import id.niteroomcreation.learningprogressstory.presenter.custom.CLoadingDialog
import id.niteroomcreation.learningprogressstory.util.PrefUtil

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
    private lateinit var emptyLayout: Dialog
    private lateinit var context: Context
    private lateinit var binding: BActivityBinding

    lateinit var prefUser: PrefUtil
    lateinit var prefApp: PrefUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateAnimInside()

        binding = BActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        progressLoading = CLoadingDialog.progressDialog(context)
        emptyLayout = CLoadingDialog.progressEmptyDialog(context) {
            emptyLayout.dismiss()
        }
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        initPref()
        onCreateInside()
        initUI()

        if (contentLayout() != EMPTY_LAYOUT) {
            try {

                var inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                inflater.inflate(contentLayout(), binding.layoutContent)

            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("Inflating contentLayout() failed on ${this.javaClass.simpleName}")
            }
        }

    }

    private fun initPref() {
        prefUser = Injection.providePrefUser()
        prefApp = Injection.providePrefApp()
    }

    fun getActContext(): Context {
        return context
    }

    override fun showEmptyState() {
        emptyLayout.setTitle(if (title == null) title else this.resources.getString(R.string.info_loading_title))
//        emptyLayout.setCancelable(false)
//        emptyLayout.setCanceledOnTouchOutside(false)

        if (!emptyLayout.isShowing) {
            runOnUiThread { emptyLayout.show() }
        }
    }

    override fun dismissEmptyState() {
        if (emptyLayout.isShowing)
            runOnUiThread { emptyLayout.dismiss() }
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
            toast = Toast.makeText(this, message, Toast.LENGTH_LONG).also { it.show() }
    }

    override fun showMessage(resId: Int) {
        showMessage(this.getString(resId))
    }
}