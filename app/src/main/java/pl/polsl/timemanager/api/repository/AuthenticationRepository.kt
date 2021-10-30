package pl.polsl.timemanager.api.repository

import io.reactivex.rxjava3.core.Single
import pl.polsl.timemanager.model.Credentials
import pl.polsl.timemanager.model.Token
import pl.polsl.timemanager.model.User

interface AuthenticationRepository {

    fun onCurrentUserChanged(user: User)

    fun login(credentials: Credentials): Single<Result<Token>>

}