package com.example.clicknchow.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentDetailBinding
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.utils.Helpers.formatPrice

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var data: Data? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as DetailActivity).toolbarDetail()
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().intent.getParcelableExtra("data", Data::class.java)
        } else {
            requireActivity().intent.getParcelableExtra<Data>("data")
        }
        initView(data)

        binding.btnOrderNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: Data?) {

        bundle = bundleOf("data" to data)

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(binding.ivFoodDetail)

        binding.tvFoodName.text = data?.name
        binding.rbFood.rating = data?.rate?.toFloat() ?: 0f
        binding.tvDescription.text = data?.description
        binding.tvIngredient.text = data?.ingredients
        binding.tvTotalPrice.formatPrice(data?.price.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}