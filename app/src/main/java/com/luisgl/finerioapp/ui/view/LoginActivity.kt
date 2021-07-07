package com.luisgl.finerioapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.luisgl.finerioapp.R
import com.luisgl.finerioapp.databinding.ActivityLoginBinding
import com.luisgl.finerioapp.model.interfaces.login.LoginListener
import com.luisgl.finerioapp.ui.utils.hide
import com.luisgl.finerioapp.ui.utils.show
import com.luisgl.finerioapp.ui.utils.showAlert
import com.luisgl.finerioapp.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginListener {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginviewmodel = viewModel
        viewModel.listener = this
    }

    override fun onSuccess(token: String, id: String, email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("token", token)
            putExtra("id", id)
        }
        startActivity(homeIntent)
        finish()
    }

    override fun onErrorLogin(typeError: Int) {
        when (typeError) {
            0 -> {
                showAlert(
                    getString(R.string.error_empty_fields),
                    getString(R.string.error_empty_fields_message)
                )
            }
            1 -> {
                showAlert(
                    getString(R.string.error_credentials),
                    getString(R.string.error_credentials_message)
                )
            }
            else -> {
                showAlert(
                    getString(R.string.error_conection),
                    getString(R.string.error_conection_message)
                )
            }
        }
    }

    override fun onShowProgress() {
        pb_login.show()
    }

    override fun onHideProgress() {
        pb_login.hide()
    }
}