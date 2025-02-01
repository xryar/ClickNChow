package com.example.clicknchow.ui.order.pastorders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicknchow.databinding.ItemPastOrderBinding
import com.example.clicknchow.utils.Helpers.convertLongToTime
import com.example.clicknchow.utils.Helpers.formatPrice

class PastOrderAdapter(private val listData: List<com.example.clicknchow.model.response.transaction.Data>) :
    RecyclerView.Adapter<PastOrderAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemPastOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPastOrderBinding.inflate(
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
            tvDate.text = data.food.createdAt.convertLongToTime("MMM dd, HH:mm")
            tvStatus.text = data.status
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