package app.mobify.orderbread.feature.activities.login

import android.app.Activity
import android.content.Intent
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.activities.base.BaseActivity
import app.mobify.orderbread.feature.utils.sharedPrefs.SharedPref
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject
import java.util.*

class LoginActivity : BaseActivity(), LoginContract.View {
    companion object {
        val rcSignIn: Int = 99
    }

    private val presenter: LoginPresenter by inject()
    private val sharedPref: SharedPref by inject()

    override fun loginSucceful() {
        val resultIntent = Intent()
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun loginError(error: String) {
        showError(getString(R.string.login_error_code).format(error)) {
            val resultIntent = Intent()
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
        }
    }

    override fun loginCanceled() {
        showError(getString(R.string.login_error_canceled)) {
            val resultIntent = Intent()
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
        }
    }

    override fun startLogin() {
        // Choose authentication providers
        val providers = Arrays.asList(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            rcSignIn
        )
    }

    override fun onStart() {
        super.onStart()
        sharedPref.activity = this
        presenter.sharedPref = sharedPref
        presenter.view = this
        presenter.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == rcSignIn) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                presenter.loginSucceful(user)
            } else {
                presenter.loginFailed(response)
            }
        }
    }
}
