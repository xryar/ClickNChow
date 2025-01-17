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

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var foodList: ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataDummy()
        val adapter = HomeAdapter(foodList)
        val layoutManager: LayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvFoodHorizontal.layoutManager = layoutManager
        binding.rvFoodHorizontal.adapter = adapter
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