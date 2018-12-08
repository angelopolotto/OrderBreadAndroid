package app.mobify.orderbread.feature.utils.sharedPrefs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import app.mobify.orderbread.R
import app.mobify.orderbread.feature.api.models.Bread
import app.mobify.orderbread.feature.api.models.Cart
import app.mobify.orderbread.feature.api.models.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder



class SharedPref : SharedPrefContract {
    private var sharedPref: SharedPreferences? = null
    get() {
        return activity.getPreferences(Context.MODE_PRIVATE)
    }
    lateinit var activity: Activity

    private var gson: Gson?

    init {
        val builder = GsonBuilder()
        this.gson = builder.create()
    }

    override fun getLoginToken(): User? {
        return gson?.fromJson(
            sharedPref?.getString(activity.resources.getString(R.string.shared_pref_user_key), null),
            User::class.java)
    }

    override fun saveLoginToken(user: User) {
        with (sharedPref?.edit()) {
            this?.putString(activity.resources.getString(R.string.shared_pref_user_key), gson?.toJson(user))
            this?.apply()
        }
    }

    override fun addToCart(bread: Bread) {
        val cartJson = sharedPref?.getString(
            activity.resources.getString(R.string.shared_pref_cart_key), null)
        val cart: Cart?
        if (cartJson != null) {
            cart = gson?.fromJson(cartJson, Cart::class.java) ?: Cart(mutableListOf())
            cart.breads.add(bread)
        } else {
            cart = Cart(mutableListOf())
            cart.breads.add(bread)
        }
        with (sharedPref?.edit()) {
            this?.putString(
                activity.resources.getString(R.string.shared_pref_cart_key),
                gson?.toJson(cart))
            this?.apply()
        }
    }

    override fun getCart(): Cart? {
        return gson?.fromJson(sharedPref?.getString(
            activity.resources.getString(R.string.shared_pref_cart_key), null),
            Cart::class.java)
    }
}