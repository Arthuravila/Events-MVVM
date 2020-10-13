package com.app.desafiosicredi.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.desafiosicredi.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToLoginActivity()
    }

    private fun goToLoginActivity() {
        Intent(this@SplashActivity, MainActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }
}
