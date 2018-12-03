package app.mobify.orderbread.feature.activities.base

interface BaseContract {
    interface View {
        fun showError(message: String)
        fun showProgress()
        fun hideProgress()
    }
}