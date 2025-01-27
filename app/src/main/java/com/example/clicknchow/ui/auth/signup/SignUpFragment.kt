package com.example.clicknchow.ui.auth.signup

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentSignUpBinding
import com.example.clicknchow.model.request.RegisterRequest
import com.example.clicknchow.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var filePath: Uri? = null
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    filePath = data?.data

                    Glide.with(this)
                        .load(filePath)
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.ivUser)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDummy()
        initListener()


    }

    private fun initListener() {
        binding.ivUser.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .compress(1024)
                .createIntent { intent ->
                    imagePickerLauncher.launch(intent)
                }
        }

        binding.btnContinue.setOnClickListener {
            val name = binding.edName.text.toString()
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            if (name.isEmpty()) {
                binding.edName.error = "Silahkan masukkan Nama anda"
                binding.edName.requestFocus()
            } else if (email.isEmpty()) {
                binding.edEmail.error = "Silahkan masukkan Email anda"
                binding.edEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.edPassword.error = "Silahkan masukkan Password anda"
                binding.edPassword.requestFocus()
            } else {
                val data = RegisterRequest(
                    name,
                    email,
                    password,
                    password,
                    "",
                    "",
                    "",
                    "",
                    filePath
                )

                val action = SignUpFragmentDirections.actionSignUpAddress(data)
                Navigation.findNavController(it).navigate(action)
                (activity as AuthActivity).toolbarSignUpAddress()
            }
        }
    }

    private fun initDummy() {
        binding.edName.setText("Fajar Pedas")
        binding.edEmail.setText("fajarpedas123@gmail.com")
        binding.edPassword.setText("12345678")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}