package com.example.clicknchow.ui.home.homeviewpager.newtaste

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicknchow.databinding.ItemFoodVerticalBinding
import com.example.clicknchow.model.response.home.Data
import com.example.clicknchow.ui.detail.DetailActivity
import com.example.clicknchow.utils.Helpers.formatPrice

class HomeNewTasteAdapter(private val listData: List<Data>) :
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
        holder.binding.tvFoodTitle.text = data.name
        holder.binding.tvFoodPrice.formatPrice(data.price.toString())
        holder.binding.rbFood.rating = data.rate.toFloat()
        Glide.with(holder.itemView.context)
            .load(data.picturePath)
            .into(holder.binding.ivFood)
        holder.itemView.setOnClickListener {
            val detail = Intent(it.context, DetailActivity::class.java)
            it.context.startActivity(detail)
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

}