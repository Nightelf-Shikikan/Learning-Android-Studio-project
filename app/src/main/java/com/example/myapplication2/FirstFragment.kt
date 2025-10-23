package com.example.myapplication2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonSend.setOnClickListener {
            val messageToSend = binding.textMessage.text.toString()

            // Create a bundle manually
            val bundle = Bundle().apply {
                putString("message", messageToSend)
            }

            // Navigate to SecondFragment with bundle
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
