package com.antyzero.paczka.app.ui.screen.parcels

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.antyzero.paczka.app.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ParcelsFragment : Fragment(R.layout.fragment_parcels) {

    private lateinit var parcelsViewModel: ParcelsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parcelsViewModel =
            ViewModelProvider(this).get(ParcelsViewModel::class.java)

        lifecycleScope.launch {
            parcelsViewModel.quote.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
