package app.mobify.orderbreadandroid.api.models.user

data class Profile(
    val uid: String,
    val email: String,
    val name: String,
    val photoUrl: String,
    val token: String
)