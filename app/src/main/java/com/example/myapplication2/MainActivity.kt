package com.example.myapplication2

import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DiffUtil


class MyAdapter(
    private var items: List<MyItem>,
    private val onButtonClick: (MyItem) -> Unit
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.itemImage)
        val name = view.findViewById<TextView>(R.id.itemName)
        val description = view.findViewById<TextView>(R.id.itemDescription)
        val button = view.findViewById<Button>(R.id.itemButton)
    }

    fun updateList(newItems: List<MyItem>) {
        val diff = DiffUtil.calculateDiff(MyDiffUtilCallback(items, newItems))
        items = newItems
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.image.setImageResource(item.imageRes)
        holder.name.text = item.name
        holder.description.text = item.description

        holder.button.setOnClickListener {
            onButtonClick(item)
        }
    }

    override fun getItemCount() = items.size

}

data class MyItem(
    val name: String,
    val description: String,
    val imageRes: Int
)


// ---------------- MainActivity -----------------
class MainActivity : AppCompatActivity() {

    var items = listOf(
        MyItem("Apple", "A tasty fruit", R.drawable.apple),
        MyItem("Banana", "Yellow friend", R.drawable.apple),
        MyItem("Cherry", "Small but powerful", R.drawable.apple)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(items) { item ->
            Toast.makeText(this, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }
        val adapter = MyAdapter(items) { item ->
            Toast.makeText(this, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        val updateButton = findViewById<Button>(R.id.updateButton)

        updateButton.setOnClickListener {
            val newList = items.toMutableList()
            newList[1] = MyItem("Orange", "Fresh fruit!", R.drawable.apple) // replace Banana
            adapter.updateList(newList)
            items = newList          // Save new list
        }
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}