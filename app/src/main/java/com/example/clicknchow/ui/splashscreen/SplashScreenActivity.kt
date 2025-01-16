package com.example.clicknchow.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.clicknchow.databinding.ActivitySplashScreenBinding
import com.example.clicknchow.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler().postDelayed({
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}