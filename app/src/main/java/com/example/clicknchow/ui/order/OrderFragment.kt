package com.example.clicknchow.ui.order

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.clicknchow.R
import com.example.clicknchow.databinding.FragmentOrderBinding
import com.example.clicknchow.model.response.transaction.Data
import com.example.clicknchow.model.response.transaction.TransactionResponse
import com.google.android.material.tabs.TabLayoutMediator

class OrderFragment : Fragment(), OrderContract.View{

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: OrderPresenter
    private var progressDialog: Dialog? = null
    private var inProgressList: ArrayList<Data>? = ArrayList()
    private var pastOrderList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = OrderPresenter(this)
        presenter.getTransaction()

        initView()
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isEmpty()) {
            binding.llEmpty.visibility = View.VISIBLE
            binding.llTab.visibility = View.GONE
            binding.includeToolbar.toolbarAuth.visibility = View.GONE
        } else {
            binding.llEmpty.visibility = View.GONE
            binding.llTab.visibility = View.VISIBLE
            binding.includeToolbar.toolbarAuth.visibility = View.VISIBLE

            for (a in transactionResponse.data.indices) {
                if(transactionResponse.data[a].status.equals("ON_DELIVERY", true)
                    || (transactionResponse.data[a].status.equals("PENDING", true))) {
                    inProgressList?.add(transactionResponse.data[a])
                }else if(transactionResponse.data[a].status.equals("DELIVERED", true)
                    || (transactionResponse.data[a].status.equals("CANCELLED", true))
                    || (transactionResponse.data[a].status.equals("SUCCESS", true))) {
                    pastOrderList?.add(transactionResponse.data[a])
                }
            }
            showViewPager()
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    private fun showViewPager() {
        val sectionPagerAdapter = OrderSectionPagerAdapter(this)
        sectionPagerAdapter.setData(inProgressList, pastOrderList)
        Log.d("OrderFragment", "InProgressList: $inProgressList, PastOrderList: $pastOrderList")
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "In Progress"
                1 -> "Past Order"
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}