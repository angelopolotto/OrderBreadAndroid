package app.mobify.orderbreadandroid.utils.cartLogic

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.user.Cart
import com.google.gson.Gson
import java.math.BigDecimal

object CartLogic {
    fun addToCart(
        cartJson: String?,
        gson: Gson?,
        bread: Bread, maxPerItem: Int, maxItemsCart: Int,
        errorMaxPerItem: (() -> Unit)? = null,
        errorMaxItemsCart: (() -> Unit)? = null,
        success: ((cart: Cart) -> Unit)? = null
    ) {
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf(), BigDecimal.ZERO)
            val breadAtCart = cart.breads.find { item -> item.id == bread.id }
            if (breadAtCart != null) {
                if (breadAtCart.quantity < maxPerItem) {
                    breadAtCart.quantity = breadAtCart.quantity + 1
                } else {
                    errorMaxPerItem?.invoke()
                    return
                }
            } else {
                if (cart.breads.size < maxItemsCart) {
                    bread.quantity = 1
                    cart.breads.add(bread)
                } else {
                    errorMaxItemsCart?.invoke()
                    return
                }
            }
        } else {
            cart = Cart(mutableListOf(), BigDecimal.ZERO)
            bread.quantity = 1
            cart.breads.add(bread)
        }
        cart.total = cart.breads.fold(BigDecimal.ZERO) { acc, bread -> calculateItemTotalPrice(bread).add(acc) } ?:
                BigDecimal(0)

        success?.invoke(cart)
    }

    fun updateFromCart(
        cartJson: String?, gson: Gson?, bread: Bread,
        error: (() -> Unit)? = null,
        success: ((cart: Cart) -> Unit)? = null
    ) {
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf(), BigDecimal.ZERO)
            if (cart.breads.isNotEmpty()) {
                val filtered = cart.breads.filter { it.id == bread.id }
                filtered[0].quantity = bread.quantity
                success?.invoke(cart)
            }
        } else {
            error?.invoke()
        }
    }

    fun removeFromCart(
        cartJson: String?, gson: Gson?, bread: Bread,
        error: (() -> Unit)? = null, success: ((cart: Cart) -> Unit)? = null
    ) {
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf(), BigDecimal.ZERO)
            if (cart.breads.isNotEmpty()) {
                val filtered = cart.breads.filter { it.id == bread.id }
                cart.breads.remove(filtered[0])
                success?.invoke(cart)
            }
        } else {
            error?.invoke()
        }
    }

    fun getCart(cartJson: String?, gson: Gson?, success: ((cart: Cart) -> Unit)? = null) {
        val cart = gson?.fromJson(cartJson, Cart::class.java)
        cart?.let {
            success?.invoke(it)
        } ?: run {
            cart = Cart(mutableListOf(), BigDecimal.ZERO)

        }
    }

    private fun calculateItemTotalPrice(item: Bread): BigDecimal {
        return item.price.multiply(BigDecimal(item.quantity))
    }
}