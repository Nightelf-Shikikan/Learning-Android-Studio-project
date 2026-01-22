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
import com.example.myapplication2.data.model.Item
class ItemAdapter(

    private val buttonText: String,
    private val onButtonClicked: (Item) -> Unit,
    private val onItemClicked: (Item) -> Unit

) : ListAdapter<Item, ItemAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(old: Item, new: Item) =
                old.id == new.id

            override fun areContentsTheSame(old: Item, new: Item) =
                old == new
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textView)
        val button: Button = view.findViewById(R.id.buttonAdd)
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

        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
    }
}