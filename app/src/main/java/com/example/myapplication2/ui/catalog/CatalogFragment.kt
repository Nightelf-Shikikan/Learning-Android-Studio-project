package com.example.myapplication2.ui.catalog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R
import com.example.myapplication2.data.model.Item
import com.example.myapplication2.data.repository.ItemAdapter
import com.example.myapplication2.viewmodel.CatalogViewModel

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val viewModel: CatalogViewModel by activityViewModels()
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