package pl.polsl.timemanager.api.repository.impl

import io.reactivex.rxjava3.core.Single
import pl.polsl.timemanager.api.RetrofitProvider
import pl.polsl.timemanager.api.repository.AuthenticationRepository
import pl.polsl.timemanager.api.service.AuthenticationService
import pl.polsl.timemanager.model.Credentials
import pl.polsl.timemanager.model.Token
import pl.polsl.timemanager.model.User

class AuthenticationRepositoryImpl : AuthenticationRepository {

    private val api = RetrofitProvider.createService(AuthenticationService::class.java)
    private lateinit var currentUser: User

    override fun onCurrentUserChanged(user: User) {
        currentUser = user
    }

    override fun login(credentials: Credentials): Single<Result<Token>> {
        return Single.create {
            api.login(credentials).enqueue(RxCallbackImpl(it))
        }
    }

}