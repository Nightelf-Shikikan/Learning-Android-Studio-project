package com.example.myapplication2.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.databinding.FragmentCatalogBinding
import com.example.myapplication2.viewmodel.CatalogViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import com.example.myapplication2.R
import com.example.myapplication2.data.model.CatalogItem

@AndroidEntryPoint
class CatalogFragment : Fragment() {


    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private lateinit var catalogAdapter: CatalogAdapter
    private lateinit var basketAdapter: CatalogAdapter

    private val viewModel: CatalogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)

        catalogAdapter = CatalogAdapter(
            items = emptyList(),
            onAddToCartClicked = { item -> viewModel.addToBasket(item) },
            onDetailsClicked = { item -> openItemDetails(item) }
        )
        binding.catalogRecyclerView.adapter = catalogAdapter

        basketAdapter = CatalogAdapter(
            items = emptyList(),
            onAddToCartClicked = { /* optional */ },
            onDetailsClicked = { item -> openItemDetails(item) }
        )
        binding.catalogRecyclerView.adapter = basketAdapter


        binding.catalogRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.catalogRecyclerView.adapter = catalogAdapter

        observeItems()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collect { catalogItems ->
                catalogAdapter.updateItems(catalogItems)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.basket.collect { basketItems ->
                basketAdapter.updateItems(basketItems)
            }
        }

        return binding.root
    }

    private fun observeItems() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { list ->
                    catalogAdapter.updateItems(list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun openItemDetails(item: CatalogItem) {
        val fragment = ItemDetailsFragment.newInstance(item)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

}