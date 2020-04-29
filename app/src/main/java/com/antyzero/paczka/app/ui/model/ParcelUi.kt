package com.antyzero.paczka.app.ui.model

sealed class ParcelUi {

    data class Simple(
        val id: Any
    )
}