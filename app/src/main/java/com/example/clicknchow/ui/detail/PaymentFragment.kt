package com.example.clicknchow.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentPaymentBinding
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.model.response.login.User
import com.example.clicknchow.utils.Helpers.formatPrice
import com.google.gson.Gson

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as DetailActivity).toolbarPayment()

        val data = arguments?.getParcelable<Data>("data")
        initView(data)

        binding.btnCheckoutNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_success)
        }
    }

    private fun initView(data: Data?) {
        data?.let {
            binding.tvFoodNameOrdered.text = data.name
            binding.tvFoodPriceOrdered.formatPrice(data.price.toString())
            binding.tvFoodQuantity.text = "1 items"
            Glide.with(requireContext())
                .load(data.picturePath)
                .into(binding.ivFoodOrdered)
            binding.tvFoodName.text = data.name
            binding.tvFoodPrice.formatPrice(data.price.toString())
            binding.tvDriverPrice.text = "50.000"

            if (data.price.toString().isNotEmpty()) {
                val totalTax = data.price?.div(10)
                binding.tvTaxPrice.formatPrice(totalTax.toString())

                total = data.price!!+totalTax!!+50000
                binding.tvAllPrice.formatPrice(total.toString())
            } else {
                binding.tvFoodPrice.text = "IDR. 0"
                binding.tvTotalPrice.text = "IDR. 0"
                binding.tvTaxPrice.text = "IDR. 0"
                total = 0
            }
        }
        val user = ClickNChow.getApp().getUser()
        if (user != null) {
            Log.d("RawJSON", user)
        }
        val userResponse = Gson().fromJson(user, User::class.java)
        binding.tvUsername.text = userResponse?.name
        binding.tvUserPhoneNumber.text = userResponse?.phoneNumber
        binding.tvUserAddress.text = userResponse?.address ?: "Alamat tidak tersedia"
        Log.d("UserData", "Address: ${userResponse?.address}")
        binding.tvUserHouseNumber.text = userResponse?.houseNumber
        binding.tvUserCity.text = userResponse?.city
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}