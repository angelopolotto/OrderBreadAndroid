package app.mobify.orderbreadandroid.activities.checkout

interface CheckoutContract {
    interface View {
        fun getLocation(location: (lat: Double, long: Double) -> Unit, error: (message: String) -> Unit)
        fun showCartError()
        fun showGetLocationError()
    }
    interface Presenter {
        fun loadData()

        fun checkout()
    }
}