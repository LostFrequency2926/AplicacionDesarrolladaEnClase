package com.danielfmunoz.myfirstform.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielfmunoz.myfirstform.R
import com.danielfmunoz.myfirstform.ui.main.SignInActivity
import com.danielfmunoz.myfirstform.ui.main.SignUpActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = Timer()
        timer.schedule(
            timerTask {
            var intent = Intent(this@SplashActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        },
            2000
        )
    }
}