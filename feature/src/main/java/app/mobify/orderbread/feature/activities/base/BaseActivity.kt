package app.mobify.orderbread.feature.activities.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import app.mobify.orderbread.R.color.textPrimary
import app.mobify.orderbread.feature.R


abstract class BaseActivity : Activity(), BaseContract.View {
    var progress: ProgressBar? = null

    fun startActivity(cls: Class<out Any>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    override fun showError(message: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.base_dialog_erro) // O Titulo da notificação
        alertDialog.setMessage(message) // a mensagem ou alerta
        alertDialog.setPositiveButton("Ok") { _, _ -> }

        val dialog = alertDialog.create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, textPrimary))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, textPrimary))
    }

    override fun showProgress() {
        progress?.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = GONE
    }
}