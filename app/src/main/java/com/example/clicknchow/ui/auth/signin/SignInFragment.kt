package com.example.clicknchow.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentSignInBinding
import com.example.clicknchow.model.response.login.LoginResponse
import com.example.clicknchow.ui.MainActivity
import com.example.clicknchow.ui.auth.AuthActivity
import com.google.gson.Gson

class SignInFragment : Fragment(), SignInContract.View {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: SignInPresenter
    private var progressDialog: Dialog? = null

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
        presenter = SignInPresenter(this)

        if (!ClickNChow.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        } else if (ClickNChow.getApp().getToken().isNullOrEmpty()) {
            val auth = Intent(activity, AuthActivity::class.java)
            startActivity(auth)
            activity?.finish()
        }

        initView()
        initDummy()

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            if (email.isEmpty()) {
                binding.edEmail.error = "Silahkan masukkan Email anda"
                binding.edEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.edPassword.error = "Silahkan masukkan Password anda"
                binding.edPassword.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }

        binding.btnRegister.setOnClickListener {
            val register = Intent(activity, AuthActivity::class.java)
            register.putExtra("pageRequest", 2)
            startActivity(register)
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        val gson = Gson().toJson(loginResponse.user)
        ClickNChow.getApp().setUser(gson)
        ClickNChow.getApp().setToken(loginResponse.access_token)

        val login = Intent(activity, MainActivity::class.java)
        startActivity(login)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initDummy() {
        binding.edEmail.setText("rusdi12322@hotbarber.com")
        binding.edPassword.setText("12345678")
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, binding.root, false)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}