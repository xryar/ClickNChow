package com.example.clicknchow.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavOptions
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
            toolbarSignUp()
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()
            navController.navigate(R.id.action_sign_up, null, navOption)
        }
    }

    private fun toolbarSignUp() {
        binding.toolbar.toolbarAuth.title = "Sign Up"
        binding.toolbar.toolbarAuth.subtitle = "Register And Eat"
        binding.toolbar.toolbarAuth.navigationIcon = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_arrow_back_000,
            null
        )
        binding.toolbar.toolbarAuth.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun toolbarSignUpAddress() {
        binding.toolbar.toolbarAuth.title = "Address"
        binding.toolbar.toolbarAuth.subtitle = "Make sure it's valid"
        binding.toolbar.toolbarAuth.navigationIcon = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_arrow_back_000,
            null
        )
        binding.toolbar.toolbarAuth.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun toolbarSignUpSuccess() {
        binding.toolbar.toolbarAuth.visibility = View.GONE
    }
}