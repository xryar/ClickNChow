package com.example.clicknchow.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.clicknchow.R
import com.example.clicknchow.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pageRequest = intent.getIntExtra("pageRequest", 0)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_auth) as? NavHostFragment
            ?: throw IllegalStateException("NavHostFragment not found")
        val navController = navHostFragment.navController
        if (pageRequest == 2) {
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()
            navController.navigate(R.id.action_sign_up, null, navOption)
        }
    }
}