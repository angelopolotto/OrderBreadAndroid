package app.mobify.orderbreadandroid.activities.cart

import app.mobify.orderbreadandroid.api.models.Bread

interface CartContract {
    interface View {
        fun showCartItems(breads: MutableList<Bread>)
    }

    interface Presenter {
        fun loadData()

    }
}