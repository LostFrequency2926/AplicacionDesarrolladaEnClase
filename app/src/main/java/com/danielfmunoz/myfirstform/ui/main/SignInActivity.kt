package com.danielfmunoz.myfirstform.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.databinding.ActivitySignInBinding
import com.danielfmunoz.myfirstform.ui.bottomMenu.MainActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var signInBinding: ActivitySignInBinding
    private lateinit var signInViewModel: SignInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        setContentView(signInBinding.root)

        signInViewModel.errorMsg.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
        signInViewModel.isSuccessSignIn.observe(this) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        signInBinding.tvSignUp.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        signInBinding.btnIngresar.setOnClickListener {
            val email = signInBinding.etCorreo.text.toString()
            val password = signInBinding.etPassword.text.toString()
            signInViewModel.validateFields(email, password)
        }
    }
}