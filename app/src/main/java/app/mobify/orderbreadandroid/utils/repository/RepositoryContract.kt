package app.mobify.orderbreadandroid.utils.repository

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.checkout.OrderVerification

interface RepositoryContract {
    fun loadBreads(result: (breads: ArrayList<Bread>)->Unit)
    fun allowCustomerLocation(
        lat: Double, long: Double,
        result: (orderVerification: OrderVerification) -> Unit
    )
}