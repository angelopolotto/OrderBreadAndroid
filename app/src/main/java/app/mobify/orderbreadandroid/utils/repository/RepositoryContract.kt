package app.mobify.orderbreadandroid.utils.repository

import app.mobify.orderbreadandroid.api.models.Bread

interface RepositoryContract {
    fun loadBreads(result: (breads: ArrayList<Bread>)->Unit)
    fun allowCustomerLocation(
        lat: Double, long: Double,
        function: (allow: Boolean, error: String) -> Unit
    )
}