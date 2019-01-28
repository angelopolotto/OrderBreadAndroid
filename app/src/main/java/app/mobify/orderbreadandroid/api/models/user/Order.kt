package app.mobify.orderbreadandroid.api.models.user

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.checkout.ShippingType
import java.math.BigDecimal
import java.util.*

data class Order(
    var number: String,
    var breads: List<Bread>,
    var total: BigDecimal,
    var orderDate: Date,
    var shippingType: ShippingType,
    var dateToGetOrder: Date,
    var addressShipping: Address,
    var addressGetOrder: Address
)