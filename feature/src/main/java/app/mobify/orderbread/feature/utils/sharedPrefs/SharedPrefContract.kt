package app.mobify.orderbread.feature.utils.sharedPrefs

import app.mobify.orderbread.feature.api.models.Bread
import app.mobify.orderbread.feature.api.models.Cart
import app.mobify.orderbread.feature.api.models.User

interface SharedPrefContract {
    fun getLoginToken(): User?
    fun saveLoginToken(user: User)
    fun addToCart(bread: Bread)
    fun getCart(): Cart?
}