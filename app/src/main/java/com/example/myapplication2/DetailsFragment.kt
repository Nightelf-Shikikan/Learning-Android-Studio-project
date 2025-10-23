package com.example.myapplication2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlin.getValue

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.textCounter)
        val btnToSettings = view.findViewById<Button>(R.id.btnToSettings)
        val btnIncrease = view.findViewById<Button>(R.id.btnIncrease)

        textView.text = viewModel.counter.toString()

        btnIncrease.setOnClickListener {
            viewModel.counter++
            textView.text = viewModel.counter.toString()
        }


        val btn = view.findViewById<Button>(R.id.btnToSettings)
        btn.setOnClickListener {
            findNavController().navigate(R.id.action_details_to_settings)
        }
    }
}