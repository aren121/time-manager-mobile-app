package pl.polsl.timemanager.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_start.*
import pl.polsl.timemanager.MainActivity
import pl.polsl.timemanager.R
import pl.polsl.timemanager.model.Credentials

class StartActivity : AppCompatActivity() {

    private lateinit var startViewModel: StartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        startViewModel = ViewModelProvider(this).get(StartViewModelImpl::class.java)
        startViewModel.checkAlreadyLogged()

        startViewModel.loginStatus.observe(this) {
            when(it) {
                LoginStatus.LOGGED -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                LoginStatus.EXPIRED -> {
                    Toast.makeText(this, R.string.sessionExpired, Toast.LENGTH_SHORT).show()
                }
            }

        }

        startViewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            loginButton.isEnabled = true
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        loginButton.setOnClickListener {
            loginButton.isEnabled = false
            startViewModel.login(Credentials(
                loginField.text.toString(),
                passwordField.text.toString()
            ))
        }

    }
}