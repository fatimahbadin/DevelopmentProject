package com.example.easy2book.Nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.easy2book.Activity.Cinema
import com.example.easy2book.Home
import com.example.easy2book.MainActivity
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DataBaseHelper(requireContext())
        var txtUsernameM = view.findViewById<TextView>(R.id.txtProfileUsername)
        txtUsernameM.text = dbHelper.getAllLoggedUsers().last().Username

        var txtUsernameSm = view.findViewById<TextView>(R.id.txtProfileUsernameSm)
        txtUsernameSm.text = dbHelper.getAllLoggedUsers().last().Username

        var txtEmail = view.findViewById<TextView>(R.id.txtProfileEmail)
        txtEmail.text = dbHelper.getAllLoggedUsers().last().Email

        var txtChangePass = view.findViewById<TextView>(R.id.txtChangePass)
        txtChangePass.setOnClickListener {
            Toast.makeText(context, "Please Sign Up Again", Toast.LENGTH_SHORT).show()
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

}