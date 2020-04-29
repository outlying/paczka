package com.antyzero.paczka.app.ui.adapter

import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antyzero.paczka.app.R
import com.antyzero.paczka.app.ui.model.ParcelUi
import kotlin.random.Random

class SimpleParcelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textView = view.findViewById<TextView>(R.id.textView)

    init {

        view.apply {
            setBackgroundColor(
                Color.rgb(
                    Random.nextInt(255),
                    Random.nextInt(255),
                    Random.nextInt(255)
                )
            )
        }

        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> v.elevation = 16f
                MotionEvent.ACTION_UP -> v.elevation = 4f
            }
            true
        }
    }

    fun bind(parcelUi: ParcelUi.Simple) {
        textView.text = parcelUi.id.toString()
    }
}