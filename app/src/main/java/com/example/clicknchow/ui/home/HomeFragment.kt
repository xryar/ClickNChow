package com.example.clicknchow.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentHomeBinding
import com.example.clicknchow.model.response.home.HomeResponse
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: HomePresenter
    private var progressDialog: Dialog? = null

    //private var foodList: ArrayList<Data> = ArrayList()

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
        presenter = HomePresenter(this)
        presenter.getHome()

        initView()
        //initDataDummy()
        showViewPager()
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

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        val adapter = HomeAdapter(homeResponse.data)
        val layoutManager: LayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvFoodHorizontal.layoutManager = layoutManager
        binding.rvFoodHorizontal.adapter = adapter
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, binding.root, false)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

//    private fun initDataDummy() {
//        foodList = ArrayList()
//        foodList.add(HomeModel("Cherry Healthy", "", 5f))
//        foodList.add(HomeModel("Burger Tamayo", "", 4f))
//        foodList.add(HomeModel("Bwang Puttie", "", 4.5f))
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}