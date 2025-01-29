package com.example.clicknchow.ui.home.homeviewpager.newtaste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clicknchow.databinding.FragmentHomeNewTasteBinding
import com.example.clicknchow.model.response.home.Data

class HomeNewTasteFragment : Fragment() {

    private var _binding: FragmentHomeNewTasteBinding? = null
    private val binding get() = _binding!!
    private var newTasteList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeNewTasteBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")
        showVerticalFood()
    }

    private fun showVerticalFood() {
        val adapter = HomeNewTasteAdapter(newTasteList!!)
        val layoutManager: LayoutManager = LinearLayoutManager(activity)
        binding.rvListFood.layoutManager = layoutManager
        binding.rvListFood.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}