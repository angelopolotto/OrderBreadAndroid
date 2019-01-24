package app.mobify.orderbreadandroid.activities.checkout

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.user.Address
import java.math.BigDecimal
import java.util.*

interface CheckoutContract {
    interface View {
        fun getLocation(
            error: (message: String) -> Unit,
            location: (lat: Double, long: Double) -> Unit
        )

        fun showErrorGeneric(error: String)
        fun showResume(resume: String)
        fun showWallet(creditCard: CreditCard)
        fun showEmptyWallet()
        fun showTotal(total: BigDecimal)
        fun showLocationsToGetOrder(addressesToGetOrder: List<Address>)
        fun showOrderDate(orderDate: Date)
        fun showDateToGetOrder(dateToGetOrder: Date)
        fun getResumeString(item: Bread): String
    }
    interface Presenter {
        fun loadData()

        fun checkout()
    }
}