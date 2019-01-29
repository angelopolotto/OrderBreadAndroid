package app.mobify.orderbreadandroid.utils.sharedPrefs

import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.user.Cart
import app.mobify.orderbreadandroid.api.models.user.Order
import app.mobify.orderbreadandroid.api.models.user.User
import app.mobify.orderbreadandroid.api.models.user.Wallet

interface SharedPrefContract {
    fun getUser(): User?
    fun saveUser(profile: User)

    fun addToCart(
        bread: Bread, maxPerItem: Int, maxItemsCart: Int,
        errorMaxPerItem: () -> Unit,
        errorMaxItemsCart: () -> Unit,
        success: (cart: Cart) -> Unit
    )

    fun updateFromCart(bread: Bread, error: () -> Unit)
    fun removeFromCart(bread: Bread, error: () -> Unit)
    fun getCart(): Cart

    fun getWallet(): Wallet
    fun addToWallet(
        creditCard: CreditCard,
        errorCardAlreadyWallet: () -> Unit,
        success: (wallet: Wallet) -> Unit
    )

    fun updateFromWallet(creditCard: CreditCard, error: () -> Unit)
    fun removeFromWallet(creditCard: CreditCard, error: () -> Unit)
    fun saveOrder(order: Order)
}