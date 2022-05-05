package com.example.easy2book.Nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.easy2book.Activity.Cinema
import com.example.easy2book.Activity.Museum
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.R

class ActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//      Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      Gets the activities from the database and sets the text accordingly
        val dbHelper = DataBaseHelper(requireContext())
        val txtAct1 = view.findViewById<TextView>(R.id.txtFragAct1)
        txtAct1.text = dbHelper.getAllActivity().first().Activity

        val txtAct2 = view.findViewById<TextView>(R.id.txtFragAct2)
        txtAct2.text = dbHelper.getAllActivity().last().Activity

//      Function for the cinema button that will take the user to the cinema page
        val btnCinema = view.findViewById<CardView>(R.id.btnCinema)
        btnCinema.setOnClickListener {
            startActivity(Intent(context, Cinema::class.java))
        }

//      Function for the museum button that will take the user to the museum page
        val btnMuseum = view.findViewById<CardView>(R.id.btnMuseum)
        btnMuseum.setOnClickListener {
            startActivity(Intent(context, Museum::class.java))
        }
    }
}