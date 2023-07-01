package com.example.clamsleepsrinath.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clamsleepsrinath.MainActivity
import com.example.clamsleepsrinath.databinding.ItemContainerBinding

class ItemsAdapter(context: MainActivity) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    var itemList = mutableListOf<Model>()
    var mainContext = context

    fun setItems(item: List<Model>){
        this.itemList = item.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContainerBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  data = itemList[position]
        holder.binding.name.text = data.Name
        holder.binding.img.setImageResource(data.image)
        holder.binding.img.setOnClickListener {
            data.isExpanded  = true
            mainContext.collapseView(data)
        }
    }

    class ViewHolder(val binding: ItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}