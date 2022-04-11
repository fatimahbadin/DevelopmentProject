package com.example.easy2book.Nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.easy2book.R
import com.example.easy2book.Transport.Bus
import com.example.easy2book.Transport.Train

class TransportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.btnBus).setOnClickListener {
            startActivity(Intent(context, Bus::class.java))
        }

        view.findViewById<CardView>(R.id.btnTrain).setOnClickListener {
            startActivity(Intent(context, Train::class.java))
        }
    }
}