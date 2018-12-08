package app.mobify.orderbread.feature.activities.login

import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseUser

interface LoginContract {
    interface View {
        fun startLogin()
        fun loginCanceled()
        fun loginError(error: String)
        fun loginSucceful()
    }
    interface Presenter {
        fun start()
        fun loginSucceful(userFirebase: FirebaseUser?)
        fun loginFailed(response: IdpResponse?)
    }
}