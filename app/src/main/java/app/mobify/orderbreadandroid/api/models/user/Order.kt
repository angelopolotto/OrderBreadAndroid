package app.mobify.orderbreadandroid.api.models.user

import app.mobify.orderbreadandroid.api.models.Bread
import java.math.BigDecimal
import java.util.*

data class Order(
    var number: String,
    var breads: List<Bread>,
    var total: BigDecimal,
    var orderDate: Date,
    var dateToGetOrder: Date,
    var addressToGetOrder: Address
)