package app.mobify.orderbreadandroid.api.models.checkout

data class ShippingType(
    val id: Int,
    val name: String,
    val description: String,
    var default: Boolean = false
)