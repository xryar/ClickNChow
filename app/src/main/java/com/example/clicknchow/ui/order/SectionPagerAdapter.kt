package com.example.clicknchow.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.ui.order.inprogress.InProgressFragment
import com.example.clicknchow.ui.order.pastorders.PastOrderFragment

class SectionPagerAdapter(fragmentManager: Fragment) : FragmentStateAdapter(fragmentManager) {

    private var newTasteList: ArrayList<Data>? = ArrayList()
    private var popularList: ArrayList<Data>? = ArrayList()
    private var recommendedList: ArrayList<Data>? = ArrayList()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment?
        when(position) {
            0 -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastOrderFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle
                return fragment
            }
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }

    fun setData(
        newTasteListParms: ArrayList<Data>?,
        popularListParms: ArrayList<Data>?,
        recommendedListParms: ArrayList<Data>?
    ) {
        newTasteList = newTasteListParms
        popularList = popularListParms
        recommendedList = recommendedListParms
    }

}