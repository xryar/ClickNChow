package com.example.clicknchow.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentSignUpAddressBinding
import com.example.clicknchow.model.request.RegisterRequest
import com.example.clicknchow.model.response.register.RegisterResponse
import com.example.clicknchow.ui.auth.AuthActivity
import com.google.gson.Gson

class SignUpAddressFragment : Fragment(), SignUpContract.View {

    private var _binding: FragmentSignUpAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var data: RegisterRequest
    private lateinit var presenter: SignUpPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpAddressBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SignUpPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!
        initDummy()
        initListener()
        initView()
    }

    private fun initListener() {
        binding.btnRegister.setOnClickListener { it ->
            val phoneNo = binding.edPhoneNo.text.toString()
            val address = binding.edAddress.text.toString()
            val houseNo = binding.edHouseNo.text.toString()
            val city = binding.edCity.text.toString()

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = houseNo
                it.phoneNumber = phoneNo
            }

            if (phoneNo.isEmpty()) {
                binding.edPhoneNo.error = "Silahkan masukkan Nomor Telepon anda"
                binding.edPhoneNo.requestFocus()
            } else if (address.isEmpty()) {
                binding.edAddress.error = "Silahkan masukkan Alamat anda"
                binding.edAddress.requestFocus()
            } else if (houseNo.isEmpty()) {
                binding.edHouseNo.error = "Silahkan masukkan Nomor Rumah anda"
                binding.edHouseNo.requestFocus()
            } else if (city.isEmpty()) {
                binding.edCity.error = "Silahkan masukkan Kota anda"
                binding.edCity.requestFocus()
            } else {
                presenter.submitRegister(data, it)
            }
        }
    }

    override fun onRegisterSuccess(registerResponse: RegisterResponse, view: View) {
        val gson = Gson().toJson(registerResponse.user)
        ClickNChow.getApp().setUser(gson)
        ClickNChow.getApp().setToken(registerResponse.access_token)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_sign_up_success, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_sign_up_success, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
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

    private fun initDummy() {
        binding.edPhoneNo.setText("0869696996")
        binding.edAddress.setText("JL. Ambatukam awoowowowo")
        binding.edHouseNo.setText("69")
        binding.edCity.setText("Jaktim")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}