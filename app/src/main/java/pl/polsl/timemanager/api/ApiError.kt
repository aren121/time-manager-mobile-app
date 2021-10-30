package pl.polsl.timemanager.api

data class ApiError(
    val status: Int,
    val name: String,
    val message: String
)
