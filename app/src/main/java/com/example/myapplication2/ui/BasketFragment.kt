package com.example.myapplication2.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication2.R
import com.example.myapplication2.data.repository.ItemAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.data.repository.BasketAdapter

class BasketFragment : Fragment(R.layout.fragment_basket) {

    private lateinit var adapter: ItemAdapter
    private val viewModel: MainViewModel by activityViewModels() // shared ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BasketAdapter("Remove") { basketItem ->
            viewModel.removeFromBasket(basketItem)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewBasket)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.basketItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }
}