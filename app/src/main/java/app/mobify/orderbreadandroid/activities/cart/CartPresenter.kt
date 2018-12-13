package app.mobify.orderbreadandroid.activities.cart

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.Cart
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbreadandroid.utils.repository.RepositoryContract
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPrefContract
import java.math.BigDecimal

class CartPresenter : CartContract.Presenter {
    lateinit var repository: RepositoryContract
    lateinit var sharedPref: SharedPrefContract
    lateinit var view: CartContract.View
    lateinit var memoryStore: MemoryStoreContract

    private var cartItems: Cart? = null
    private var total: BigDecimal = BigDecimal(0)

    override fun loadData() {
        cartItems = sharedPref.getCart()
        cartItems?.let {
            view.showCartItems(it.breads)
            calculateAndShowTotal()
        }
    }

    override fun removeItem(item: Bread) {
        sharedPref.removeFromCart(item)
        loadData()
    }

    override fun updateTotalValue(item: Bread) {
        sharedPref.updateFromCart(item)
        calculateAndShowTotal()
    }

    private fun calculateAndShowTotal() {
        total = cartItems?.breads?.fold(BigDecimal(0)) { acc, bread -> calculateItemTotalPrice(bread).add(acc) } ?:
                BigDecimal(0)
        view.showTotalValue(total)
    }

    override fun calculateItemTotalPrice(item: Bread): BigDecimal {
        return item.price.multiply(BigDecimal(item.quantity))
    }

    override fun checkout() {
        view.showCheckout()
    }

    override fun maxValuePerItem(): Int {
        return memoryStore.maxPerItem
    }

    override fun maxValuePerItemError() {
        view.errorMaxPerItem(memoryStore.maxPerItem)
    }
}