package com.example.clicknchow.ui.home.customtab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clicknchow.ui.home.customtab.newtaste.HomeNewTasteFragment
import com.example.clicknchow.ui.home.customtab.popular.HomePopularFragment
import com.example.clicknchow.ui.home.customtab.recommended.HomeRecommendedFragment

class SectionPagerAdapter(fragmentManager: Fragment) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = HomeNewTasteFragment()
            1 -> fragment = HomePopularFragment()
            2 -> fragment = HomeRecommendedFragment()
        }
        return fragment as Fragment
    }

}