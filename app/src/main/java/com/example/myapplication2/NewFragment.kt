package com.example.myapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class NewFragment : Fragment(R.layout.fragment_new) {




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate a simple layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false)
    }
}

