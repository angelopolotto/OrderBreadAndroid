package app.mobify.orderbread.feature.breadList

import app.mobify.orderbread.feature.api.models.BreadItem

interface BreadListContract {
    interface View {
        fun showBreads(breads: ArrayList<BreadItem>)
        fun showDetails(bread: BreadItem)
    }
    interface Presenter {
        fun loadBreads()
        fun showDetailsBreads(bread: BreadItem)
    }
}