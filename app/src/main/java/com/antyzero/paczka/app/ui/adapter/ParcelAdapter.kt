package com.antyzero.paczka.app.ui.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.antyzero.paczka.app.R
import com.antyzero.paczka.app.ui.model.ParcelUi
import kotlin.random.Random

object ParcelAdapter :
    ListAdapter<ParcelUi.Simple, SimpleParcelViewHolder>(SimpleParcelItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleParcelViewHolder {
        return SimpleParcelViewHolder(
            View.inflate(
                parent.context,
                R.layout.item_parcel_simple,
                null
            ).apply {
                setBackgroundColor(
                    Color.rgb(
                        Random.nextInt(255),
                        Random.nextInt(255),
                        Random.nextInt(255)
                    )
                )
            }
        )
    }

    override fun onBindViewHolder(holder: SimpleParcelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}