package com.example.clicknchow.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicknchow.databinding.ItemFoodHorizontalBinding
import com.example.clicknchow.model.dummy.HomeModel
import com.example.clicknchow.ui.detail.DetailActivity

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
            holder.itemView.setOnClickListener {
                val detail = Intent(it.context, DetailActivity::class.java)
                it.context.startActivity(detail)
            }
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

}