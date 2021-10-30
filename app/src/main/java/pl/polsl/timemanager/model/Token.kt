package pl.polsl.timemanager.model

import java.util.*

data class Token (
    var token: String,
    var expirationDate: Date,
    var user: User
)