package com.example.easy2book.Nav

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

//      Values to retrieve the database helper and the text views on the profile page
//      The text view gets the last row of data from the user logged table and
//      sets the text accordingly
        val dbHelper = DataBaseHelper(requireContext())
        var txtUsernameM = view.findViewById<TextView>(R.id.txtProfileUsername)
        txtUsernameM.text = dbHelper.getAllLoggedUsers().last().Username

        var txtUsernameSm = view.findViewById<TextView>(R.id.txtProfileUsernameSm)
        txtUsernameSm.text = dbHelper.getAllLoggedUsers().last().Username

        var txtEmail = view.findViewById<TextView>(R.id.txtProfileEmail)
        txtEmail.text = dbHelper.getAllLoggedUsers().last().Email

//      Function for the sign out button, a dialog box will appear asking the user
//      if they are sure they want to sign out. If no the dialog box will close if yes the user
//      will be taken to the login page.
        val btnSignOut = view.findViewById<Button>(R.id.btnSignOut)
        btnSignOut.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Are you sure you want to Sign Out?")

            builder.setPositiveButton("Yes") { dialog, which ->
                startActivity(Intent(context, MainActivity::class.java))
            }

            builder.setNegativeButton("No") { dialog, whihc ->
                dialog.cancel()
            }

            builder.show()
        }

    }

}