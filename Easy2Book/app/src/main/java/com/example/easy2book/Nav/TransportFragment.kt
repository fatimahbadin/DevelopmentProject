package com.example.easy2book.Nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.R
import com.example.easy2book.Transport.Bus
import com.example.easy2book.Transport.Train

class TransportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//      Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      Gets the transport from the database and sets the text accordingly
        val dbHelper = DataBaseHelper(requireContext())
        val txtTrans1 = view.findViewById<TextView>(R.id.txtFragTrans1)
        txtTrans1.text = dbHelper.getAllTransport().first().Transport

        val txtTrans2 = view.findViewById<TextView>(R.id.txtFragTrans2)
        txtTrans2.text = dbHelper.getAllTransport().last().Transport

//      Function for the bus button that will take the user to the bus page
        view.findViewById<CardView>(R.id.btnBus).setOnClickListener {
            startActivity(Intent(context, Bus::class.java))
        }

//      Function for the train button that will take the user to the train page
        view.findViewById<CardView>(R.id.btnTrain).setOnClickListener {
            startActivity(Intent(context, Train::class.java))
        }
    }
}