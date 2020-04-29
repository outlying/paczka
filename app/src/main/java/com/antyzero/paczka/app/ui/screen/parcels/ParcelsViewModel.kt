package com.antyzero.paczka.app.ui.screen.parcels

import androidx.lifecycle.ViewModel
import com.antyzero.paczka.app.ui.model.ParcelUi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class ParcelsViewModel : ViewModel() {

    private val _activeParcels = ConflatedBroadcastChannel<List<ParcelUi.Simple>>().apply {
        val parcels = (0..10).map { ParcelUi.Simple(it) }
        offer(parcels)
    }

    val activeParcels: Flow<List<ParcelUi.Simple>>
        get() = _activeParcels.asFlow()
}