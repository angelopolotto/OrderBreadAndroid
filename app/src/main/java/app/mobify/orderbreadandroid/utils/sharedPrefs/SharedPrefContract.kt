package app.mobify.orderbreadandroid.utils.sharedPrefs

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.Cart
import app.mobify.orderbreadandroid.api.models.User

interface SharedPrefContract {
    fun getUser(): User?
    fun saveUser(user: User)
    fun addToCart(
        bread: Bread, maxPerItem: Int, maxItemsCart: Int,
        success: () -> Unit,
        errorMaxPerItem: () -> Unit,
        errorMaxItemsCart: () -> Unit
    )

    fun removeFromCart(bread: Bread)
    fun getCart(): Cart?
    fun updateFromCart(bread: Bread)
}