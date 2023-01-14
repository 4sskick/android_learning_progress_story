package id.niteroomcreation.learningprogressstory.presenter.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.niteroomcreation.learningprogressstory.databinding.BActivityBinding
import id.niteroomcreation.learningprogressstory.presenter.custom.CLoadingDialog

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    companion object {
        val TAG: String = BaseActivity.javaClass.simpleName
        const val EMPTY_LAYOUT: Int = 0
    }

    private lateinit var toast: Toast
    private lateinit var progressLoading: Dialog
    private lateinit var context: Context
    private lateinit var binding: BActivityBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = BActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        progressLoading = CLoadingDialog.progressDialog(context)

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
}