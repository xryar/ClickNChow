package com.example.clicknchow.ui.profile.profileviewpager.clicknchow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clicknchow.databinding.FragmentProfileAccountBinding

class ProfileClickNChowFragment : Fragment() {

    private var _binding: FragmentProfileAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileAccountBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

}