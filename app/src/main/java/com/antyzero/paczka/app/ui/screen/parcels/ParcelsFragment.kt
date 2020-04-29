package com.antyzero.paczka.app.ui.screen.parcels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.antyzero.paczka.app.R
import com.antyzero.paczka.app.ui.adapter.ParcelAdapter
import com.antyzero.paczka.app.ui.model.ParcelUi
import kotlinx.android.synthetic.main.fragment_parcels.*

class ParcelsFragment : Fragment(R.layout.fragment_parcels) {

    private val parcelsViewModel: ParcelsViewModel by lazy {
        ViewModelProvider(this).get(ParcelsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = ParcelAdapter.apply {
            submitList((0..5).map { ParcelUi.Simple(it) })
        }
    }
}
