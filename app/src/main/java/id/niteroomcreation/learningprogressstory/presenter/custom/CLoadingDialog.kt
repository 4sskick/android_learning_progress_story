package id.niteroomcreation.learningprogressstory.presenter.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import id.niteroomcreation.learningprogressstory.R

/**
 * Created by Septian Adi Wijaya on 14/01/2023.
 * please be sure to add credential if you use people's code
 */
class CLoadingDialog {

    companion object {
        val TAG = CLoadingDialog.javaClass.simpleName

        private lateinit var dialog: Dialog

        fun progressDialog(context: Context): Dialog {
            dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.c_loading, null)

            dialog.setContentView(inflate)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            return dialog
        }
    }
}