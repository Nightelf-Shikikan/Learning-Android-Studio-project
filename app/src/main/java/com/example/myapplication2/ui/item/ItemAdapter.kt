package com.example.myapplication2.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.viewmodel.ItemViewModel
import com.example.myapplication2.data.model.Item

import com.example.myapplication2.R
import com.example.myapplication2.databinding.ItemRowBinding

class ItemAdapter(
    private val viewModel: ItemViewModel
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var items = listOf<Item>()

    fun submitList(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowBinding.bind(itemView)

        fun bind(item: Item) {
            // Set text and checkbox
            binding.item.text = item.content
            binding.checkbox.isChecked = item.checkbox

            // Toggle checkbox in database via ViewModel
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                viewModel.update(item.copy(checkbox = isChecked))
            }

            // Delete item via ViewModel
            binding.btnDelete.setOnClickListener {
                viewModel.delete(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}