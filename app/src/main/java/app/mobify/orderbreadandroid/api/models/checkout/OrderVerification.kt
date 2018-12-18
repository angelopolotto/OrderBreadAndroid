package app.mobify.orderbreadandroid.api.models.checkout

import app.mobify.orderbreadandroid.api.models.user.Address
import java.util.*

data class OrderVerification(
    var allowLocation: Boolean,
    var errorLocation: String,
    var addressesToGetOrder: List<Address>,
    var orderDate: Date,
    var dateToGetOrder: Date
)