package app.mobify.orderbreadandroid.utils.walletLogic

import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.user.Wallet
import com.google.gson.Gson

object WalletLogic {
    fun addToWallet(
        walletJson: String?,
        gson: Gson?,
        creditCard: CreditCard,
        errorCardAlreadyWallet: (() -> Unit)? = null,
        success: ((wallet: Wallet) -> Unit)? = null
    ) {
        val wallet: Wallet?
        if (walletJson != null) {
            wallet = gson?.fromJson(walletJson, Wallet::class.java) ?: Wallet(mutableListOf())
            val cardInTheWallet = wallet.creditCards.find { item -> item.cardNumber == creditCard.cardNumber }
            if (cardInTheWallet != null) {
                errorCardAlreadyWallet?.invoke()
                return
            } else {
                wallet.creditCards.add(creditCard)
            }
        } else {
            wallet = Wallet(mutableListOf())
            wallet.creditCards.add(creditCard)
        }
        success?.invoke(wallet)
    }

    fun updateFromWallet(
        walletJson: String?, gson: Gson?, creditCard: CreditCard,
        error: (() -> Unit)? = null,
        success: ((wallet: Wallet) -> Unit)? = null
    ) {
        val wallet: Wallet?
        if (walletJson != null) {
            wallet = gson?.fromJson(walletJson, Wallet::class.java) ?: Wallet(mutableListOf())
            if (wallet.creditCards.isNotEmpty()) {
                val filtered = wallet.creditCards.filter { it.cardNumber == creditCard.cardNumber }
                filtered[0].securityCode = creditCard.securityCode
                filtered[0].expirationDate = creditCard.expirationDate
                filtered[0].cardHolder = creditCard.cardHolder
                success?.invoke(wallet)
            }
        } else {
            error?.invoke()
        }
    }

    fun removeFromWallet(
        walletJson: String?, gson: Gson?, creditCard: CreditCard,
        error: (() -> Unit)? = null,
        success: ((wallet: Wallet) -> Unit)? = null
    ) {
        val wallet: Wallet?
        if (walletJson != null) {
            wallet = gson?.fromJson(walletJson, Wallet::class.java) ?: Wallet(mutableListOf())
            if (wallet.creditCards.isNotEmpty()) {
                val filtered = wallet.creditCards.filter { it.cardNumber == creditCard.cardNumber }
                wallet.creditCards.remove(filtered[0])
                success?.invoke(wallet)
            }
        } else {
            error?.invoke()
        }
    }

    fun getWallet(cartJson: String?, gson: Gson?, success: ((cart: Cart) -> Unit)? = null): Cart {
        val cart = gson?.fromJson(cartJson, Cart::class.java)
        cart.let {
            success?.invoke(it)
            return it
        } ?: run {
            val cartNew = Cart(mutableListOf(), BigDecimal.ZERO)
            success?.invoke(cartNew)
            return cartNew
        }
    }
}