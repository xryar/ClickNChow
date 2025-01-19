package com.example.clicknchow.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clicknchow.ui.profile.profileviewpager.account.ProfileAccountFragment
import com.example.clicknchow.ui.profile.profileviewpager.clicknchow.ProfileClickNChowFragment

class SectionPagerAdapter(fragmentManager: Fragment) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = ProfileAccountFragment()
            1 -> fragment = ProfileClickNChowFragment()
        }
        return fragment as Fragment
    }

}