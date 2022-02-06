package id.ac.kprm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.ac.kprm.databinding.ActivitySplashScreenBinding
import id.ac.kprm.ui.login.LoginActivity
import id.ac.kprm.ui.login.UserSession

class SplashScreen : AppCompatActivity() {
    private lateinit var userSession: UserSession
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userSession = UserSession(this)

        val handler = Handler()
        handler.postDelayed({
            val dest = if (!userSession.checkIsLogin()) LoginActivity::class.java else MainActivity::class.java
            val intent = Intent(this, dest)
            startActivity(intent)
        }, 2000)
    }
}