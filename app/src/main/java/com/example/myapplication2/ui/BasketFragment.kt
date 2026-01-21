package com.example.myapplication2.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R
import com.example.myapplication2.data.repository.BasketAdapter
import com.example.myapplication2.data.model.BasketItem

class BasketFragment : Fragment(R.layout.fragment_basket) {

    private lateinit var adapter: BasketAdapter
    private val viewModel: MainViewModel by activityViewModels()

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