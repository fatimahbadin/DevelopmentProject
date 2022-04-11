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

        var txtActivity = findViewById<TextView>(R.id.txtActivityC)
        var txtATime = findViewById<TextView>(R.id.txtATimeC)
        var txtMovie = findViewById<TextView>(R.id.txtMovieC)
        var txtExhibit = findViewById<TextView>(R.id.txtExhibitC)
        var txtTransport = findViewById<TextView>(R.id.txtTransportC)
        var txtLocationFrom = findViewById<TextView>(R.id.txtLFromC)
        var txtLocationTo = findViewById<TextView>(R.id.txtLToC)
        var txtDepartTime = findViewById<TextView>(R.id.txtDepartC)
        var txtNoOfPeople = findViewById<TextView>(R.id.txtNoOfPeopleC)

        txtActivity.text = lastDetail.Activity
        txtATime.text = lastDetail.TimeBooked
        txtMovie.text = lastDetail.MovieName
        txtExhibit.text = lastDetail.Exhibition
        txtTransport.text = lastDetail.Transport
        txtLocationFrom.text = lastDetail.LocationFrom
        txtLocationTo.text = lastDetail.LocationTo
        txtDepartTime.text = lastDetail.DepartTime
        txtNoOfPeople.text = lastDetail.NoOfPeople
    }

    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }

}