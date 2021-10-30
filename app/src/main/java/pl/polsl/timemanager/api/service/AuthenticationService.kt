package pl.polsl.timemanager.api.service

import pl.polsl.timemanager.model.Credentials
import pl.polsl.timemanager.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("login")
    fun login(@Body credentials: Credentials): Call<Token>

}