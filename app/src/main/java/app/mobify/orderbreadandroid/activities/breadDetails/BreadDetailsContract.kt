package app.mobify.orderbreadandroid.activities.breadDetails

import app.mobify.orderbreadandroid.api.models.Bread

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