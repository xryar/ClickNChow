package com.example.clicknchow.ui.order.pastorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clicknchow.databinding.FragmentPastOrderBinding
import com.example.clicknchow.databinding.FragmentProfileAccountBinding
import com.example.clicknchow.model.dummy.ProfileMenuModel
import com.example.clicknchow.ui.profile.ProfileAdapter

class PastOrderFragment : Fragment() {

    private var _binding: FragmentPastOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPastOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}