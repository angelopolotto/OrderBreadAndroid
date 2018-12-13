package app.mobify.orderbreadandroid.activities.base

import android.app.Activity
import android.app.AlertDialog
import android.support.v4.content.ContextCompat
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import app.mobify.orderbreadandroid.R

abstract class BaseActivity : Activity(), BaseContract.View {
    var progress: ProgressBar? = null

    override fun showError(message: String) {
        showError(message, null)
    }

    override fun showError(message: String, okCalback: (() -> Unit)?) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.base_dialog_erro) // O Titulo da notificação
        alertDialog.setMessage(message) // a mensagem ou alerta
        alertDialog.setPositiveButton("Ok") { _, _ -> okCalback?.invoke() }

        val dialog = alertDialog.create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.textPrimary))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.textPrimary))
    }

    override fun showProgress() {
        progress?.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = GONE
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}