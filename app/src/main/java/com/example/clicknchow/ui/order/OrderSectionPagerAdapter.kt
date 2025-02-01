package com.example.clicknchow.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clicknchow.model.response.transaction.Data
import com.example.clicknchow.ui.order.inprogress.InProgressFragment
import com.example.clicknchow.ui.order.pastorders.PastOrderFragment

class OrderSectionPagerAdapter(fragmentManager: Fragment) : FragmentStateAdapter(fragmentManager) {

    private var inProgressList: ArrayList<Data>? = ArrayList()
    private var pastOrderList: ArrayList<Data>? = ArrayList()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment?
        when(position) {
            0 -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastOrderFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrderList)
                fragment.arguments = bundle
                return fragment
            }
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }

    fun setData(
        inProgressParms: ArrayList<Data>?,
        pastOrderParms: ArrayList<Data>?
    ) {
        inProgressList = inProgressParms
        pastOrderList = pastOrderParms
        notifyDataSetChanged()
    }

}