package app.mobify.orderbreadandroid.activities.base

import android.app.AlertDialog
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import app.mobify.orderbreadandroid.R


abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    var progress: ProgressBar? = null

    override fun showError(message: String) {
        showError(message, null)
    }

    override fun showInfo(title: String, message: String, okCalback: (() -> Unit)?) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title) // O Titulo da notificação
        alertDialog.setMessage(message) // a mensagem ou alerta
        alertDialog.setPositiveButton("Ok") { _, _ -> okCalback?.invoke() }

        val dialog = alertDialog.create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.textPrimary))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.textPrimary))
    }

    override fun showInfo(message: String, okCallback: (() -> Unit)?) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.base_dialog_info) // O Titulo da notificação
        alertDialog.setMessage(message) // a mensagem ou alerta
        alertDialog.setPositiveButton("Ok") { _, _ -> okCallback?.invoke() }

        val dialog = alertDialog.create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.textPrimary))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.textPrimary))
    }

    override fun showError(message: String, okCallback: (() -> Unit)?) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.base_dialog_erro) // O Titulo da notificação
        alertDialog.setMessage(message) // a mensagem ou alerta
        alertDialog.setPositiveButton("Ok") { _, _ -> okCallback?.invoke() }

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

    fun showMultiple(title: String, items: List<String>, okCallback: ((option: Int) -> Unit)?) {
        val b = AlertDialog.Builder(this)
        b.setTitle(title)
        b.setItems(items.toTypedArray()) { dialog, which ->
            dialog.dismiss()
            okCallback?.invoke(which)
        }
        b.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}