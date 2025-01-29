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
import com.bumptech.glide.Glide
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentHomeBinding
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.model.response.home.HomeResponse
import com.example.clicknchow.model.response.login.User
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment : Fragment(), HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: HomePresenter
    private var progressDialog: Dialog? = null

    private var newStateList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()
    private var recommendedList: ArrayList<Data> = ArrayList()

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
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        for (a in homeResponse.data.indices) {
            val items:List<String> = homeResponse.data[a].types.split(",")
            for (x in items.indices) {
                if(items[x].equals("new_food", true)) {
                    newStateList.add(homeResponse.data[a])
                } else if(items[x].equals("recommended", true)) {
                    recommendedList.add(homeResponse.data[a])
                } else if(items[x].equals("popular", true)) {
                    popularList.add(homeResponse.data[a])
                }
            }

        }
        showViewPager()

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

    private fun showViewPager() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.setData(newStateList, popularList, recommendedList)
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

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, binding.root, false)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        var user = Gson().fromJson(ClickNChow.getApp().getUser(), User::class.java)
        if (!user.profile_photo_url.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(user.profile_photo_url)
                .into(binding.ivUser)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}