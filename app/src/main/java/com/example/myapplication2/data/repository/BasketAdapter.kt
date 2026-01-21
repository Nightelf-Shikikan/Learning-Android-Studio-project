package com.example.myapplication2.data.repository

import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication2.data.model.BasketItem

class BasketAdapter(
    private val buttonText: String,
    private val onButtonClicked: (BasketItem) -> Unit
) : ListAdapter<BasketItem, BasketAdapter.ViewHolder>(DIFF_CALLBACK) {

    // ... similar to ItemAdapter, but uses BasketItem

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.text.text = item.content
        holder.button.text = buttonText
        holder.button.setOnClickListener { onButtonClicked(item) }
    }
}