package app.mobify.orderbread.feature.activities.login

import app.mobify.orderbread.feature.api.models.User
import app.mobify.orderbread.feature.utils.sharedPrefs.SharedPrefContract
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseUser

class LoginPresenter: LoginContract.Presenter {
    override fun start() {
        view.startLogin()
    }

    override fun loginSucceful(userFirebase: FirebaseUser?) {
        userFirebase?.getIdToken(true)?.addOnCompleteListener {
            if (it.isSuccessful) {
                val idToken = it.result!!.token
                if (idToken != null) {
                    val user = User(
                        userFirebase.email ?: "Email not informed",
                        userFirebase.displayName ?: "Name not informed",
                        idToken
                    )
                    sharedPref.saveLoginToken(user)
                    view.loginSucceful()
                } else {
                    view.loginError("Problem to receive the token")
                }
            } else {
                view.loginError(it.exception.toString())
            }
        }
    }

    override fun loginFailed(response: IdpResponse?) {
        // Sign in failed. If response is null the user canceled the
        // sign-in flow using the back button. Otherwise check
        // response.getError().getErrorCode() and handle the error.
        if (response == null) {
            view.loginCanceled()
        } else {
            view.loginError(response.error?.errorCode.toString())
        }
    }

    lateinit var sharedPref: SharedPrefContract
    lateinit var view: LoginContract.View
}