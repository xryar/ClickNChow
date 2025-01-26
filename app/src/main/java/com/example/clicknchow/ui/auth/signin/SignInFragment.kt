package com.example.clicknchow.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentSignInBinding
import com.example.clicknchow.model.response.login.LoginResponse
import com.example.clicknchow.ui.MainActivity
import com.example.clicknchow.ui.auth.AuthActivity

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

        initView()

        binding.btnLogin.setOnClickListener {
//            val email = binding.edEmail.text.toString()
//            val password = binding.edPassword.text.toString()
            presenter.submitLogin("rusdi12322@hotbarber.com", "12345678")
        }

        binding.btnRegister.setOnClickListener {
            val register = Intent(activity, AuthActivity::class.java)
            register.putExtra("pageRequest", 2)
            startActivity(register)
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        val login = Intent(activity, MainActivity::class.java)
        startActivity(login)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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