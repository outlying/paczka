package com.antyzero.paczka.app.ui.adapter

import android.graphics.Color
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
    }

    fun bind(parcelUi: ParcelUi.Simple) {
        textView.text = parcelUi.id.toString()
    }
}