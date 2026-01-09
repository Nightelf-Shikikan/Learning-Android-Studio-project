package com.example.myapplication2.ui.customload

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myapplication2.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val messageTextView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)
        messageTextView = findViewById(R.id.textViewMessage)
        visibility = GONE
    }

    fun show(message: String? = null) {
        message?.let { messageTextView.text = it }
        alpha = 0f
        visibility = VISIBLE
        animate().alpha(1f).setDuration(200).start()
    }

    fun hide() {
        animate()
            .alpha(0f)
            .setDuration(200)
            .withEndAction { visibility = GONE }
            .start()
    }
}