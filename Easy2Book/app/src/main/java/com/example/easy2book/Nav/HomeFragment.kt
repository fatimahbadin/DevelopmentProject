package com.example.easy2book.Nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

//        val navController = Navigation.findNavController(this, R.id.fragment)
//        NavigationUI.setupWithNavController(navView, navController)
//
//        val txtTitle = findViewById<TextView>(R.id.txtTitle)
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            txtTitle.text = destination.label
//        }
    }

    override fun onClick(v: View?) {
//        btnActivity.setOnClickListener {
//          setContentView(R.layout.fragment_activity)
//      }
    }

}