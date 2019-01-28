package app.mobify.orderbreadandroid.api.models.user

data class Address(
    var name: String,
    var street: String,
    var number: String,
    var neighborhood: String,
    var state: String,
    var zipCode: String,
    var country: String,
    var default: Boolean
)