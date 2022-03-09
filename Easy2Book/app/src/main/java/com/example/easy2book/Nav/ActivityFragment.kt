package com.example.easy2book.Nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.easy2book.Activity.Cinema
import com.example.easy2book.Activity.Museum
import com.example.easy2book.Home
import com.example.easy2book.R

class ActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.btnCinema).setOnClickListener {
            startActivity(Intent(context, Cinema::class.java))
        }

        view.findViewById<CardView>(R.id.btnMuseum).setOnClickListener {
            startActivity(Intent(context, Museum::class.java))
        }
    }
}