package com.danielfmunoz.myfirstform.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.R
import com.danielfmunoz.myfirstform.databinding.ActivitySplashBinding
import com.danielfmunoz.myfirstform.ui.bottomMenu.MainActivity
import com.danielfmunoz.myfirstform.ui.main.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.timerTask

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var auth: FirebaseAuth = Firebase.auth

    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel

    private var isSessionActive = false

    override fun onCreate(savedInstanceState: Bundle?) {

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.validateSessionActive()

        splashViewModel.isSessionActive.observe(this){_isSessionActive ->
            isSessionActive = _isSessionActive
        }

        val timer = Timer()
        timer.schedule(
            timerTask {

                if (isSessionActive) {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(this@SplashActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
        },
            2000
        )
    }
}