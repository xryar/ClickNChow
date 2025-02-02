package com.example.clicknchow.ui.order.inprogress

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clicknchow.databinding.FragmentInProgressBinding
import com.example.clicknchow.model.response.transaction.Data

class InProgressFragment : Fragment() {

    private var _binding: FragmentInProgressBinding? = null
    private val binding get() = _binding!!
    private var adapter: InProgressAdapter? = null
    private var inProgressList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inProgressList =  if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList("data")
        } else {
            arguments?.getParcelableArrayList("data", Data::class.java)
        }
        if (!inProgressList.isNullOrEmpty()) {
            adapter = InProgressAdapter(inProgressList!!)
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