package com.antyzero.paczka.app.ui.screen.parcels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ParcelsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is parcels Fragment"
    }
    val text: LiveData<String> = _text
}