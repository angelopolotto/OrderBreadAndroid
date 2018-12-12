package app.mobify.orderbreadandroid.api.models

data class User(
    val uid: String,
    val email: String,
    val name: String,
    val photoUrl: String,
    val token: String
)