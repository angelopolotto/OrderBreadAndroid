package app.mobify.orderbreadandroid.activities.base

interface BaseContract {
    interface View {
        fun showError(message: String)
        fun showError(message: String, okCallback: (() -> Unit)?)
        fun showProgress()
        fun hideProgress()
        fun showInfo(message: String, okCallback: (() -> Unit)?)
        fun showInfo(title: String, message: String, okCalback: (() -> Unit)?)
    }
}