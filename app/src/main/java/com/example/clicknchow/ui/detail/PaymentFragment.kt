package com.example.clicknchow.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentPaymentBinding
import com.example.clicknchow.model.response.checkout.CheckoutResponse
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.model.response.login.User
import com.example.clicknchow.utils.Helpers.formatPrice
import com.google.gson.Gson

class PaymentFragment : Fragment(), PaymentContract.View {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private var total: Int = 0
    private var progressDialog: Dialog? = null
    private lateinit var presenter: PaymentPresenter

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
        presenter = PaymentPresenter(this)

        val data = arguments?.getParcelable<Data>("data")
        initFoodView(data)
    }

    private fun initFoodView(data: Data?) {
        //mengambil data food
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

        // mengambil data user
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

        binding.btnCheckoutNow.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )

        }
    }

    private fun initProgressDialog() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, binding.root, false)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initUserView() {

    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = (Uri.parse(checkoutResponse.paymentUrl))
        startActivity(intent)

        Navigation.findNavController(view).navigate(R.id.action_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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