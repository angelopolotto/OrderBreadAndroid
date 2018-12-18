package app.mobify.orderbreadandroid.activities.cart

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.user.Cart
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

    override fun loadData() {
        updateCartTotalList()
    }

    override fun removeItem(item: Bread) {
        sharedPref.removeFromCart(item) { view.errorRemoveItem() }
        updateCartTotalList()
    }

    override fun updateTotalValue(item: Bread) {
        sharedPref.updateFromCart(item) { view.errorUpdateTotal() }
        updateCartTotalList(false)
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

    private fun updateCartTotalList(updateList: Boolean = true) {
        cartItems = sharedPref.getCart()
        cartItems?.let {
            if (updateList)
                view.showCartItems(it.breads)
            view.showTotalValue(it.total)
        }
    }
}