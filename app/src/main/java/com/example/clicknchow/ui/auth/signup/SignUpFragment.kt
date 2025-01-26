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

        binding.btnContinue.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_sign_up_address, null)
            (activity as AuthActivity).toolbarSignUpAddress()
        }
    }

    private fun initListener() {
        binding.ivUser.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .createIntent { intent ->
                    imagePickerLauncher.launch(intent)
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