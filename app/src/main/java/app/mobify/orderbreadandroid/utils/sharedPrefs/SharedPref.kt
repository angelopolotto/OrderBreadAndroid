package app.mobify.orderbreadandroid.utils.sharedPrefs

import android.content.SharedPreferences
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.user.Cart
import app.mobify.orderbreadandroid.api.models.user.Profile
import app.mobify.orderbreadandroid.api.models.user.Wallet
import app.mobify.orderbreadandroid.utils.cartLogic.CartLogic
import app.mobify.orderbreadandroid.utils.walletLogic.WalletLogic
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class SharedPref : SharedPrefContract {
    var sharedPref: SharedPreferences? = null

    private var gson: Gson?
    private val userKey = "USER_KEY"
    private val cartKey = "CART_KEY"
    private val walletKey: String = "CARDS_KEY"

    init {
        val builder = GsonBuilder()
        this.gson = builder.create()
    }

    override fun getProfile(): Profile? {
        val user = gson?.fromJson(
            sharedPref?.getString(userKey, null),
            Profile::class.java
        )
        return user
    }

    override fun saveProfile(profile: Profile) {
        val edit = sharedPref?.edit()
        edit?.putString(userKey, gson?.toJson(profile))
        edit?.apply()
    }

    override fun addToCart(
        bread: Bread, maxPerItem: Int, maxItemsCart: Int,
        errorMaxPerItem: () -> Unit,
        errorMaxItemsCart: () -> Unit,
        success: (cart: Cart) -> Unit
    ) {
        val cartJson = sharedPref?.getString(cartKey, null)
        CartLogic.addToCart(
            cartJson, gson, bread, maxPerItem, maxItemsCart,
            errorMaxPerItem, errorMaxItemsCart
        ) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
            success(it)
        }
    }

    override fun updateFromCart(bread: Bread, error: () -> Unit) {
        val cartJson = sharedPref?.getString(cartKey, null)
        CartLogic.updateFromCart(cartJson, gson, bread, error) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
        }
    }

    override fun removeFromCart(bread: Bread, error: () -> Unit) {
        val cartJson = sharedPref?.getString(cartKey, null)
        CartLogic.removeFromCart(cartJson, gson, bread, error) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
        }
    }

    override fun getCart(): Cart {
        val cartJson = sharedPref?.getString(cartKey, null)
        return CartLogic.getCart(cartJson, gson) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
        }
    }

    override fun addToWallet(
        creditCard: CreditCard,
        errorCardAlreadyWallet: () -> Unit,
        success: (wallet: Wallet) -> Unit
    ) {
        val walletJson = sharedPref?.getString(walletKey, null)
        WalletLogic.addToWallet(walletJson, gson, creditCard, errorCardAlreadyWallet) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
            success(it)
        }
    }

    override fun updateFromWallet(creditCard: CreditCard, error: () -> Unit) {
        val walletJson = sharedPref?.getString(walletKey, null)
        WalletLogic.updateFromWallet(walletJson, gson, creditCard, error) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
        }
    }

    override fun removeFromWallet(creditCard: CreditCard, error: () -> Unit) {
        val walletJson = sharedPref?.getString(walletKey, null)
        WalletLogic.removeFromWallet(walletJson, gson, creditCard, error) {
            val edit = sharedPref?.edit()
            edit?.putString(cartKey, gson?.toJson(it))
            edit?.apply()
        }
    }

    override fun getWallet(): Wallet {
        val wallet = gson?.fromJson(
            sharedPref?.getString(walletKey, null),
            Wallet::class.java
        )
        return wallet
    }
}