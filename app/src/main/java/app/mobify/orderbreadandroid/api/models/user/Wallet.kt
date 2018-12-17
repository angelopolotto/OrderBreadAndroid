package app.mobify.orderbreadandroid.api.models.user

import app.mobify.orderbreadandroid.api.models.cielo.CreditCard

data class Wallet(var creditCards: MutableList<CreditCard>)