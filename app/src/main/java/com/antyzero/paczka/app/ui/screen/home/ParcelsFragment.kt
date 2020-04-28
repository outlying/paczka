package com.antyzero.paczka.app.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.antyzero.paczka.app.R

class ParcelsFragment : Fragment() {

    private lateinit var parcelsViewModel: ParcelsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parcelsViewModel =
            ViewModelProvider(this).get(ParcelsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_parcels, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        parcelsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
