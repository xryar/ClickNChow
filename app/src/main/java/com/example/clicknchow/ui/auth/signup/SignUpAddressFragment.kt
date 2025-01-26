package com.example.clicknchow.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentSignUpAddressBinding
import com.example.clicknchow.model.request.RegisterRequest
import com.example.clicknchow.ui.auth.AuthActivity

class SignUpAddressFragment : Fragment() {

    private var _binding: FragmentSignUpAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var data: RegisterRequest

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

        data = arguments?.getParcelable<RegisterRequest>("data")!!
        initDummy()
        initListener()
    }

    private fun initListener() {
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
        }

        binding.btnRegister.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_sign_up_success, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        }
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