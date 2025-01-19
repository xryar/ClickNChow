package com.example.clicknchow.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clicknchow.databinding.FragmentHomeBinding
import com.example.clicknchow.model.dummy.HomeModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var foodList: ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataDummy()
        showViewPager()
        showHorizontalFood()
    }

    private fun showHorizontalFood() {
        val adapter = HomeAdapter(foodList)
        val layoutManager: LayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvFoodHorizontal.layoutManager = layoutManager
        binding.rvFoodHorizontal.adapter = adapter
    }

    private fun showViewPager() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "New Taste"
                1 -> "Popular"
                2 -> "Recommended"
                else -> ""
            }
        }.attach()
    }

    private fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeModel("Cherry Healthy", "", 5f))
        foodList.add(HomeModel("Burger Tamayo", "", 4f))
        foodList.add(HomeModel("Bwang Puttie", "", 4.5f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}