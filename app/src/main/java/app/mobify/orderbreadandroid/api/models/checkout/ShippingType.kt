package app.mobify.orderbreadandroid.api.models.checkout

data class ShippingType(
    val id: Int,
    val name: String,
    val description: String,
    var type: Type,
    var default: Boolean = false
) {
    enum class Type {
        DELIVERY, GET_ORDER
    }
}