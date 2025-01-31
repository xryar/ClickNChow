package com.example.clicknchow.ui.order.inprogress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clicknchow.databinding.FragmentInProgressBinding
import com.example.clicknchow.databinding.FragmentProfileAccountBinding
import com.example.clicknchow.model.dummy.ProfileMenuModel
import com.example.clicknchow.ui.profile.ProfileAdapter

class InProgressFragment : Fragment() {

    private var _binding: FragmentInProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

}