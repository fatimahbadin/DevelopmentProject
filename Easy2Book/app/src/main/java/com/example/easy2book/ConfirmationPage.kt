package com.example.easy2book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.easy2book.Model.DataBaseHelper

class ConfirmationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_page)

        val dbHelper = DataBaseHelper(this)
        val lastDetail = dbHelper.getAllConfirmDetails().last()

        var txtUsername = findViewById<TextView>(R.id.txtUsernameC)
        var txtEmail = findViewById<TextView>(R.id.txtEmailC)
        var txtActOrTrans = findViewById<TextView>(R.id.txtActOrTrans)
        var txtTime = findViewById<TextView>(R.id.txtTimeC)
        var txtMovOrEx = findViewById<TextView>(R.id.txtMovOrEx)
        var txtLocationFrom = findViewById<TextView>(R.id.txtLFromC)
        var txtLocationTo = findViewById<TextView>(R.id.txtLToC)
        var txtDate = findViewById<TextView>(R.id.txtDateC)
        var txtNoOfPeople = findViewById<TextView>(R.id.txtNoOfPeopleC)

        txtUsername.text = "Username: ${lastDetail.Username}"
        txtEmail.text = "Email: ${lastDetail.Email}"
        txtDate.text = "Date: ${lastDetail.Date}"
        txtNoOfPeople.text = "Number of People Booked: ${lastDetail.NoOfPeople}"

        if (lastDetail.Transport == "" && lastDetail.Activity == "Cinema") {
            txtActOrTrans.text = "Activity Booked: " + lastDetail.Activity
            txtTime.text = "Time Booked: " + lastDetail.TimeBooked
            txtMovOrEx.text = "Movie Name: " + lastDetail.MovieName
        } else if (lastDetail.Transport == "" && lastDetail.Activity == "Museum") {
            txtActOrTrans.text = "Activity Booked: " + lastDetail.Activity
            txtTime.text = "Time Booked: " + lastDetail.TimeBooked
            txtMovOrEx.text = "Exhibit Booked: " + lastDetail.Exhibition
        } else if (lastDetail.Activity == "") {
            txtActOrTrans.text = "Transport Booked: " + lastDetail.Transport
            txtLocationFrom.text = "Location From: " + lastDetail.LocationFrom
            txtLocationTo.text = "Location To: " + lastDetail.LocationTo
            txtTime.text = "Depart Time: " + lastDetail.DepartTime
        }
    }

    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }

}