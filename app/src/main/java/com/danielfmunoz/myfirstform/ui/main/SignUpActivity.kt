package com.danielfmunoz.myfirstform.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivitySignupBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivitySignupBinding.inflate(layoutInflater)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        setContentView(mainBinding.root)

        signUpViewModel.errorMsg.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }

        signUpViewModel.isSuccessSignUp.observe(this) {
            onBackPressedDispatcher.onBackPressed()
        }

        mainBinding.btnIngresar.setOnClickListener {
            validarCampos()
        }
    }

    private fun validarCampos() {
        val nombre = mainBinding.etNombre.text.toString()
        val correo = mainBinding.etCorreo.text.toString()
        val password = mainBinding.etPassword.text.toString()
        val repetirPassword = mainBinding.etRepetirPassword.text.toString()

        signUpViewModel.validateFields(correo, password, repetirPassword)

    }
}
