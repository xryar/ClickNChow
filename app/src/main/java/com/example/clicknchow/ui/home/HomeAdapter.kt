package com.example.clicknchow.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicknchow.databinding.ItemFoodHorizontalBinding
import com.example.clicknchow.model.dummy.HomeModel

class HomeAdapter(private val listData: List<HomeModel>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemFoodHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.binding.tvTitle.text = data.title
        holder.binding.rbFood.rating = data.rating
        Glide.with(holder.itemView.context)
            .load(data.src)
            .into(holder.binding.ivProduct)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, data.title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

}