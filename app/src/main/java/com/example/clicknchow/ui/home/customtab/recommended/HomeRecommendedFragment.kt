package com.example.clicknchow.ui.home.customtab.recommended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clicknchow.databinding.FragmentHomeNewTasteBinding

class HomeRecommendedFragment : Fragment() {

    private var _binding: FragmentHomeNewTasteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeNewTasteBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}