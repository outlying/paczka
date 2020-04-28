package com.antyzero.paczka.app.ui.screen.parcels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

class ParcelsViewModel : ViewModel() {

    private val _quote = ConflatedBroadcastChannel<String>()

    val quote: Flow<String>
        get() = _quote.asFlow()
}