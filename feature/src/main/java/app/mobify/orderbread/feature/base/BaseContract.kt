package app.mobify.orderbread.feature.base

interface BaseContract {
    interface View {
        fun showError(message: String)
        fun showProgress()
        fun hideProgress()
    }
}