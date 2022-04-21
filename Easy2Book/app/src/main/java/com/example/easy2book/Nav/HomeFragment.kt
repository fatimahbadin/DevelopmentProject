package com.example.easy2book.Nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.easy2book.R

class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      Function for the activity button that will take the user to the activity page
        val btnActivity = view.findViewById<CardView>(R.id.btnActivity)
        btnActivity.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment, ActivityFragment()).commit()
        }

//      Function for the transport button that will take the user to the transport page
        val btnTransport = view.findViewById<CardView>(R.id.btnTransport)
        btnTransport.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment, TransportFragment()).commit()
      }

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}