package com.example.myapplication2

import android.os.Bundle

import android.view.View

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.Button
import androidx.fragment.app.viewModels
import android.widget.TextView

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val textView = view.findViewById<TextView>(R.id.textCounter)
        textView.text = viewModel.counter.toString()
        val btnToDetails = view.findViewById<Button>(R.id.btnToDetails)
        val btnIncrease = view.findViewById<Button>(R.id.btnIncrease)



        val btn = view.findViewById<Button>(R.id.btnToDetails)
        btn.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_details)
        }
        btnIncrease.setOnClickListener {
            viewModel.counter++
            textView.text = viewModel.counter.toString()
        }
        btnToDetails.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_details)
        }

    }


}