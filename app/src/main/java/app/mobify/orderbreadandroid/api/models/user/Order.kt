package app.mobify.orderbreadandroid.api.models.user

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.checkout.ShippingType
import app.mobify.orderbreadandroid.api.models.cielo.Payment
import java.math.BigDecimal
import java.util.*

data class Order(
    var merchantOrderId: String,
    var breads: List<Bread>,
    var total: BigDecimal,
    var orderDate: Date,
    var shippingType: ShippingType,
    var dateToGetOrder: Date? = null,
    var limitDataToGetOrder: Date? = null,
    var addressShipping: Address? = null,
    var addressGetOrder: Address? = null,
    var payment: Payment? = null
)