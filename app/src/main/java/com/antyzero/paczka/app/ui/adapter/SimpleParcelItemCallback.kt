package com.antyzero.paczka.app.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.antyzero.paczka.app.ui.model.ParcelUi

object SimpleParcelItemCallback : DiffUtil.ItemCallback<ParcelUi.Simple>() {

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