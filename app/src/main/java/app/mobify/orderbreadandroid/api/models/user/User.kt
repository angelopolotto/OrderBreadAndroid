package app.mobify.orderbreadandroid.api.models.user

data class User(
    var cart: Cart,
    var profile: Profile,
    var wallet: Wallet,
    var addressesShipping: List<Address>,
    var orders: Orders
)