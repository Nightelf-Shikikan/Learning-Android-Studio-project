package com.example.myapplication2.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.data.repository.ItemAdapter
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import androidx.fragment.app.activityViewModels
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication2.data.model.Item

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: ItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemAdapter(
            buttonText = "Add",
            onButtonClicked = { item ->
                viewModel.addToBasket(item)
                Toast.makeText(
                    requireContext(),
                    "${item.content} added",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onItemClicked = { item ->
                showItemDescription(item)
            }
        )


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Observe LiveData from ViewModel
        viewModel.catalogItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }
    private fun showItemDescription(item: Item) {
        AlertDialog.Builder(requireContext())
            .setTitle("Item details")
            .setMessage(item.content)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}