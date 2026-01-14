package com.example.myapplication2.ui.customload

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle

import android.view.View

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


import android.animation.AnimatorListenerAdapter
import android.graphics.Color


import com.example.myapplication2.databinding.ActivityCustomLoadBinding

import com.example.myapplication2.viewmodel.CustomLoadViewModel


class CustomLoadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomLoadBinding
    private val viewModel: CustomLoadViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityCustomLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        viewModel.loadData()


        binding.btnCustomLoad.setOnClickListener {
            binding.btnCustomLoad.visibility = View.INVISIBLE
            binding.circleLoader.visibility = View.VISIBLE

            binding.circleLoader.setCircleColor(Color.RED)
            val colorAnimator = ValueAnimator.ofArgb(Color.RED, Color.GREEN)
            colorAnimator.duration = 5000
            colorAnimator.addUpdateListener {
                binding.circleLoader.setCircleColor(it.animatedValue as Int)
            }
            colorAnimator.start()
            val animator = ValueAnimator.ofFloat(0f, 1f)
            animator.duration = 5000L
            animator.addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                binding.circleLoader.setProgress(progress)
            }
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.circleLoader.visibility = View.GONE
                    binding.btnCustomLoad.visibility = View.VISIBLE
                }
            })
            animator.start()
        }

    }



    private fun observeViewModel() {
        viewModel.message.observe(this) { text ->

        }
    }
}