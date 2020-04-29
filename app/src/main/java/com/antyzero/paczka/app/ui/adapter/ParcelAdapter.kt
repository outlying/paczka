package com.antyzero.paczka.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.antyzero.paczka.app.R
import com.antyzero.paczka.app.ui.model.ParcelUi

object ParcelAdapter :
    ListAdapter<ParcelUi.Simple, SimpleParcelViewHolder>(SimpleParcelItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleParcelViewHolder {
        return SimpleParcelViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_parcel_simple,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: SimpleParcelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}