package com.antyzero.paczka.app.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.antyzero.paczka.app.R
import com.antyzero.paczka.app.ui.model.ParcelUi

class ParcelAdapter : ListAdapter<ParcelUi.Simple, SimpleParcelViewHolder>(ParcelAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleParcelViewHolder {
        return SimpleParcelViewHolder(
            View.inflate(
                parent.context,
                R.layout.item_parcel_simple,
                parent
            )
        )
    }

    override fun onBindViewHolder(holder: SimpleParcelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object : DiffUtil.ItemCallback<ParcelUi.Simple>() {

        override fun areItemsTheSame(oldItem: ParcelUi.Simple, newItem: ParcelUi.Simple): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ParcelUi.Simple,
            newItem: ParcelUi.Simple
        ): Boolean {
            return oldItem == newItem
        }
    }
}