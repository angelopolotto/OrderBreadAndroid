package app.mobify.orderbreadandroid.api.models.checkout

import app.mobify.orderbreadandroid.api.models.user.Address
import java.util.*

data class OrderVerification(
    var allowLocation: Boolean,
    var errorLocation: String,
    var shippingTypes: List<ShippingType>,
    var addressesGetOrder: List<Address>,
    var orderNumber: String,
    var orderDate: Date,
    var dateToGetOrder: Date
)