package pl.polsl.timemanager.start

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import pl.polsl.timemanager.api.RepositoryHolder
import pl.polsl.timemanager.api.RetrofitProvider
import pl.polsl.timemanager.base.subscribeAndPush
import pl.polsl.timemanager.model.Credentials
import pl.polsl.timemanager.model.Token
import java.util.*

class StartViewModelImpl(application: Application) : StartViewModel(application) {

    override val loginStatus = MutableLiveData<LoginStatus>()
    private val authenticationRepository = RepositoryHolder.authenticationRepository
    private val gson = Gson()
    private val sharedPreferences =
        getApplication<Application>().getSharedPreferences("app_session", Context.MODE_PRIVATE)

    override fun checkAlreadyLogged() {
        sharedPreferences.getString("token", null)?.let {
            val token = gson.fromJson(it, Token::class.java)
            if(token.expirationDate.after(Date()))
                onIsAuthenticated(token)
            else
                loginStatus.value = LoginStatus.EXPIRED
        }
    }

    override fun login(credentials: Credentials) {

        compositeDisposable.subscribeAndPush(
                authenticationRepository.login(credentials), ::onError) { result ->

            result.onSuccess { token ->
                sharedPreferences.edit().run {
                    putString("token", gson.toJson(token))
                    apply()
                }
                onIsAuthenticated(token)

            }.onFailure(::onError)

        }

    }

    private fun onIsAuthenticated(token: Token) {
        loginStatus.value = LoginStatus.LOGGED
        RetrofitProvider.setToken(token.token)
        authenticationRepository.onCurrentUserChanged(token.user)
    }

}
