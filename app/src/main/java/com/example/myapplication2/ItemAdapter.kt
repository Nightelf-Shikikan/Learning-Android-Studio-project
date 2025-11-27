package com.example.myapplication2

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.myapplication2.databinding.ItemRowBinding


class ItemAdapter(
    private val viewModel: ItemViewModel
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var items: List<Item> = emptyList()

    // ViewHolder
    inner class ItemViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.tvContent.text = item.content
            binding.checkbox.isChecked = item.checkbox

            // Checkbox change listener
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (item.checkbox != isChecked) {
                    viewModel.toggleItem(item, isChecked)
                }
            }

            // Optional: delete button
            binding.btnDelete.setOnClickListener {
                viewModel.deleteItem(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // Call this from activity when data updates

    fun setItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}

