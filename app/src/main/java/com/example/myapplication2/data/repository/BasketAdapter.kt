package com.example.myapplication2.data.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R
import com.example.myapplication2.data.model.BasketItem

class BasketAdapter(
    private val buttonText: String,
    private val onButtonClicked: (BasketItem) -> Unit
) : ListAdapter<BasketItem, BasketAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BasketItem>() {
            override fun areItemsTheSame(old: BasketItem, new: BasketItem) = old.itemId == new.itemId
            override fun areContentsTheSame(old: BasketItem, new: BasketItem) = old == new
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textView)
        val button: Button = view.findViewById(R.id.buttonAdd) // reuse your layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.text.text = item.content
        holder.button.text = buttonText
        holder.button.setOnClickListener { onButtonClicked(item) }
    }
}