package app.mobify.orderbread.feature.utils.sharedPrefs

import app.mobify.orderbread.feature.api.models.Bread
import app.mobify.orderbread.feature.api.models.Cart
import app.mobify.orderbread.feature.api.models.User

interface SharedPrefContract {
    fun getUser(): User?
    fun saveUser(user: User)
    fun addToCart(bread: Bread)
    fun getCart(): Cart?
}