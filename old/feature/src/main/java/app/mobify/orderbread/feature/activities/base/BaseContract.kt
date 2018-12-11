package app.mobify.orderbread.feature.activities.base

interface BaseContract {
    interface View {
        fun showError(message: String)
        fun showError(message: String, okCalback: (() -> Unit)?)
        fun showProgress()
        fun hideProgress()
    }
}