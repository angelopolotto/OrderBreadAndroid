package app.mobify.orderbread.feature.activities.breadDetails

import app.mobify.orderbread.feature.api.models.Bread

interface BreadDetailsContract {
    interface View {
        fun showDetails(bread: Bread)
        fun startLogin()
        fun addedToCart()
    }
    interface Presenter {
        fun loadDetails()
        fun orderBread()
    }
}