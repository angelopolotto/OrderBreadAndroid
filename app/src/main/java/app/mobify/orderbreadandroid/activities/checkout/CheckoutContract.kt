package app.mobify.orderbreadandroid.activities.checkout

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.checkout.ShippingType
import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.user.Address
import app.mobify.orderbreadandroid.api.models.user.Wallet
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
        fun showOrderDate(orderDate: Date)
        fun showDateToGetOrder(dateToGetOrder: Date)
        fun getResumeString(item: Bread): String
        fun showShippingType(currentShippingType: ShippingType)
        fun showShippingTypesDialog(currentShippingType: ShippingType, shippingTypes: List<ShippingType>)
        fun showWalletDialog(currentCreditCard: CreditCard, wallet: Wallet)
        fun showAddressGetOrder(currentAddressToGetOrder: Address)
        fun showLocationsToGetOrder(currentAddressToGetOrder: Address, addressesToGetOrder: List<Address>)
        fun showEmptyAddress()
        fun showShippingAddresses(currentShippingAddress: Address, shippingAddresses: List<Address>)
        fun showErrorSaveOrder()
    }
    interface Presenter {
        fun loadData()
        fun showShippingTypes()
        fun showAddresses()
        fun showPlacesGetOrder()
        fun showListCreditCard()
        fun payOrder()
    }
}