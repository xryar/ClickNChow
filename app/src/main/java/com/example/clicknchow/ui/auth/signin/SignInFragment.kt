package com.example.clicknchow.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clicknchow.databinding.FragmentSignInBinding
import com.example.clicknchow.ui.MainActivity
import com.example.clicknchow.ui.auth.AuthActivity

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val login = Intent(activity, MainActivity::class.java)
            startActivity(login)
            activity?.finish()
        }

        binding.btnRegister.setOnClickListener {
            val register = Intent(activity, AuthActivity::class.java)
            register.putExtra("pageRequest", 2)
            startActivity(register)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}