package app.mobify.orderbreadandroid.activities.cart

import app.mobify.orderbreadandroid.api.models.Bread
import java.math.BigDecimal

interface CartContract {
    interface View {
        fun showCartItems(breads: MutableList<Bread>)
        fun showTotalValue(total: BigDecimal)
        fun showCheckout()
        fun errorMaxPerItem(maxPerItem: Int)
    }

    interface Presenter {
        fun loadData()
        fun calculateItemTotalPrice(item: Bread): BigDecimal
        fun removeItem(item: Bread)
        fun updateTotalValue(item: Bread)
        fun checkout()
        fun maxValuePerItem(): Int
        fun maxValuePerItemError()
    }
}