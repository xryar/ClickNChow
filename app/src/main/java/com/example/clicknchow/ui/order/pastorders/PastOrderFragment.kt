package com.example.clicknchow.ui.order.pastorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clicknchow.databinding.FragmentPastOrderBinding
import com.example.clicknchow.model.response.transaction.Data

class PastOrderFragment : Fragment() {

    private var _binding: FragmentPastOrderBinding? = null
    private val binding get() = _binding!!
    private var adapter: PastOrderAdapter? = null
    private var pastOrderList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPastOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pastOrderList = arguments?.getParcelableArrayList("data")
        if (!pastOrderList.isNullOrEmpty()) {
            adapter = PastOrderAdapter(pastOrderList!!)
            val layoutManager = LinearLayoutManager(activity)
            binding.rvMenuItem.layoutManager = layoutManager
            binding.rvMenuItem.adapter = adapter
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}