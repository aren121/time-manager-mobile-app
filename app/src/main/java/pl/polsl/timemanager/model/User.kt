package pl.polsl.timemanager.model

data class User(
    var idUser: Int,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var roleId: Int,
    var positionId: Int,
    var active: Boolean
)