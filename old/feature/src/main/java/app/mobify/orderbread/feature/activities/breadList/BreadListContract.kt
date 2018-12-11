package app.mobify.orderbread.feature.activities.breadList

import app.mobify.orderbread.feature.api.models.Bread

interface BreadListContract {
    interface View {
        fun showBreads(breads: ArrayList<Bread>)
        fun showDetails()
        fun showCart(total: Int)
    }
    interface Presenter {
        fun loadData()
        fun showDetailsBreads(bread: Bread)
    }
}