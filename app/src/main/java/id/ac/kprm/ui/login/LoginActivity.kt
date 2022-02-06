package id.ac.kprm.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.ac.kprm.databinding.ActivityLoginBinding
import id.ac.kprm.ui.MainActivity
import id.ac.kprm.ui.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userSession: UserSession
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Login"
        userSession = UserSession(this)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.btnSubmit.setOnClickListener {
            val nim = binding.etNim.text.toString()
            val pass = binding.etPassword.text.toString()

            fetching()
            validateInput(nim, pass)
        }

        viewModel.success.observe(this, {
            if (it) {
                directMain()
            }
        })

        viewModel.msg.observe(this, {
            unblockInteraction()
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun validateInput(nim: String, pass: String) {
        when {
            nim.isNullOrEmpty() -> {
                showToast("NIM")
            }
            pass.isNullOrEmpty() -> {
                showToast("Password")
            }
            else -> viewModel.login(nim, pass)
        }
    }

    private fun showToast(msg: String) {
        unblockInteraction()
        Toast.makeText(this, "$msg is required", Toast.LENGTH_SHORT).show()
    }

    private fun fetching() {
        binding.progbar.visibility = View.VISIBLE
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun unblockInteraction() {
        binding.progbar.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun directMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}