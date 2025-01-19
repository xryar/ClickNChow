package com.example.clicknchow.ui.home.homeviewpager.recommended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clicknchow.databinding.FragmentHomeNewTasteBinding
import com.example.clicknchow.model.dummy.HomeVerticalModel
import com.example.clicknchow.ui.home.homeviewpager.newtaste.HomeNewTasteAdapter

class HomeRecommendedFragment : Fragment() {

    private var _binding: FragmentHomeNewTasteBinding? = null
    private val binding get() = _binding!!

    private var foodList: ArrayList<HomeVerticalModel> = ArrayList()

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

        initDataDummy()
        showVerticalFood()
    }

    private fun showVerticalFood() {
        val adapter = HomeNewTasteAdapter(foodList)
        val layoutManager: LayoutManager = LinearLayoutManager(activity)
        binding.rvListFood.layoutManager = layoutManager
        binding.rvListFood.adapter = adapter
    }

    private fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeVerticalModel("Cherry Healthy", "10000", "",5f))
        foodList.add(HomeVerticalModel("Burger Tamayo", "25000", "",4f))
        foodList.add(HomeVerticalModel("Bwang Puttie", "5000","", 4.5f))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}