package id.niteroomcreation.learningprogressstory.presenter.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.RelativeLayout
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

        fun progressEmptyDialog(context: Context, listener: OnClickListener?): Dialog {
            dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.c_empty, null)

            dialog.setContentView(inflate)

            val parent: RelativeLayout = dialog.findViewById(R.id.empty_parent_layout)
            parent.setOnClickListener {
                listener?.onClick(it)
            }
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            return dialog
        }
    }

}