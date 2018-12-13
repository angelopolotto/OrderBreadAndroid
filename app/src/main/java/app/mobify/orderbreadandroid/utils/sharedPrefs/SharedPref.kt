package app.mobify.orderbreadandroid.utils.sharedPrefs

import android.content.SharedPreferences
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.Cart
import app.mobify.orderbreadandroid.api.models.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class SharedPref : SharedPrefContract {
    var sharedPref: SharedPreferences? = null

    private var gson: Gson?
    private val userKey = "USER_KEY"
    private val cartKey = "CART_KEY"

    init {
        val builder = GsonBuilder()
        this.gson = builder.create()
    }

    override fun getUser(): User? {
        val user = gson?.fromJson(
            sharedPref?.getString(userKey, null),
            User::class.java)
        return user
    }

    override fun saveUser(user: User) {
        val edit = sharedPref?.edit()
        edit?.putString(userKey, gson?.toJson(user))
        edit?.apply()
    }

    override fun addToCart(
        bread: Bread, maxPerItem: Int, maxItemsCart: Int,
        success: () -> Unit,
        errorMaxPerItem: () -> Unit,
        errorMaxItemsCart: () -> Unit
    ) {
        val cartJson = sharedPref?.getString(cartKey, null)
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf())
            val breadAtCart = cart.breads.find { item -> item.id == bread.id }
            if (breadAtCart != null) {
                if (breadAtCart.quantity < maxPerItem) {
                    breadAtCart.quantity = breadAtCart.quantity + 1
                } else {
                    errorMaxPerItem()
                    return
                }
            } else {
                if (cart.breads.size < maxItemsCart) {
                    bread.quantity = 1
                    cart.breads.add(bread)
                } else {
                    errorMaxItemsCart()
                    return
                }
            }
        } else {
            cart = Cart(mutableListOf())
            bread.quantity = 1
            cart.breads.add(bread)
        }
        val edit = sharedPref?.edit()
        edit?.putString(cartKey, gson?.toJson(cart))
        edit?.apply()
        success()
    }

    override fun updateFromCart(bread: Bread) {
        val cartJson = sharedPref?.getString(cartKey, null)
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf())
            if (cart.breads.isNotEmpty()) {
                val filtered = cart.breads.filter { it.id == bread.id }
                filtered[0].quantity = bread.quantity

                val edit = sharedPref?.edit()
                edit?.putString(cartKey, gson?.toJson(cart))
                edit?.apply()
            }
        }
    }

    override fun removeFromCart(bread: Bread) {
        val cartJson = sharedPref?.getString(cartKey, null)
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf())
            if (cart.breads.isNotEmpty()) {
                val filtered = cart.breads.filter { it.id == bread.id }
                cart.breads.remove(filtered[0])
                val edit = sharedPref?.edit()
                edit?.putString(cartKey, gson?.toJson(cart))
                edit?.apply()
            }
        }
    }

    override fun getCart(): Cart? {
        val cart = gson?.fromJson(
            sharedPref?.getString(cartKey, null),
            Cart::class.java)
        return cart
    }
}