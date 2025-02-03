package com.example.clicknchow.ui.detail

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.clicknchow.R
import com.example.clicknchow.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_detail) as NavHostFragment
            val navController = navHostFragment.navController
            val bundle = Bundle()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.putParcelable("data", it.getParcelable("data", Parcelable::class.java))
            } else {
                bundle.putParcelable("data", it.getParcelable("data"))
            }
            navController.setGraph(navController.graph, bundle)
        }
    }

    fun toolbarPayment() {
        binding.toolbar.toolbarAuth.visibility = View.VISIBLE
        binding.toolbar.toolbarAuth.title = "Payment"
        binding.toolbar.toolbarAuth.subtitle = "You deserve better meal"
        binding.toolbar.toolbarAuth.navigationIcon = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_arrow_back_000,
            null
        )
        binding.toolbar.toolbarAuth.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun toolbarDetail() {
        binding.toolbar.toolbarAuth.visibility = View.GONE
    }
}