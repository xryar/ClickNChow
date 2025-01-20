package com.example.clicknchow.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clicknchow.databinding.ItemMenuProfileBinding
import com.example.clicknchow.model.dummy.ProfileMenuModel

class ProfileAdapter(private val listData: List<ProfileMenuModel>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemMenuProfileBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMenuProfileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.binding.tvTitle.text = data.title
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, data.title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

}