package com.example.clicknchow.ui.home.homeviewpager.newtaste

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicknchow.databinding.ItemFoodVerticalBinding
import com.example.clicknchow.model.dummy.HomeVerticalModel
import com.example.clicknchow.utils.Helpers.formatPrice

class HomeNewTasteAdapter(private val listData: List<HomeVerticalModel>) :
    RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemFoodVerticalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.binding.tvFoodTitle.text = data.title
        holder.binding.tvFoodPrice.formatPrice(data.price)
        holder.binding.rbFood.rating = data.rating
        Glide.with(holder.itemView.context)
            .load(data.src)
            .into(holder.binding.ivFood)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, data.title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

}