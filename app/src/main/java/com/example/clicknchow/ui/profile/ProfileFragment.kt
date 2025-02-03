package com.example.clicknchow.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.databinding.FragmentProfileBinding
import com.example.clicknchow.model.response.login.User
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showUserInfo()
        showViewPager()
    }

    private fun showUserInfo(){
        val user = ClickNChow.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)
        binding.tvUsername.text = userResponse.name
        binding.tvGmail.text = userResponse.email
        Glide.with(requireActivity())
            .load(userResponse.profile_photo_url)
            .circleCrop()
            .into(binding.ivUser)
    }

    private fun showViewPager() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Account"
                1 -> "ClickNChow"
                else -> ""
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}