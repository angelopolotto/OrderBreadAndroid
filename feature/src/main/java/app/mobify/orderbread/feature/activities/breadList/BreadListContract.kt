package app.mobify.orderbread.feature.activities.breadList

import app.mobify.orderbread.feature.api.models.Bread

interface BreadListContract {
    interface View {
        fun showBreads(breads: ArrayList<Bread>)
        fun showDetails()
    }
    interface Presenter {
        fun loadBreads()
        fun showDetailsBreads(bread: Bread)
    }
}