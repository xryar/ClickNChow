package com.example.clicknchow.ui.order.inprogress

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicknchow.databinding.ItemFoodHorizontalBinding
import com.example.clicknchow.databinding.ItemProgressBinding
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.ui.detail.DetailActivity
import com.example.clicknchow.utils.Helpers.formatPrice

class InProgressAdapter(private val listData: List<com.example.clicknchow.model.response.transaction.Data>) :
    RecyclerView.Adapter<InProgressAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemProgressBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProgressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        with(holder.binding) {
            tvFoodName.text = data.food.name
            tvFoodPrice.formatPrice(data.food.price.toString())
            Glide.with(holder.itemView.context)
                .load(data.food.picturePath)
                .into(ivFood)
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

}