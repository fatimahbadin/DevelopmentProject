package com.example.easy2book.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.easy2book.ConfirmationPage
import com.example.easy2book.Home
import com.example.easy2book.Model.ConfirmDetails
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.R

class Cinema : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)

        val dbHelper = DataBaseHelper(this)

        val movieName1 = dbHelper.getAllCinema().get(0).MovieName
        val movieName2 = dbHelper.getAllCinema().get(1).MovieName
        val rdbtnMovie1 = findViewById<RadioButton>(R.id.rdbtnMovie1)
        val rdbtnMovie2 = findViewById<RadioButton>(R.id.rdbtnMovie2)

        rdbtnMovie1.text = movieName1
        rdbtnMovie2.text = movieName2

        val time1M1 = dbHelper.getAllCinema().get(0).MovieStartTime1
        val time2M1 = dbHelper.getAllCinema().get(0).MovieStartTime2

        val time1M2 = dbHelper.getAllCinema().get(1).MovieStartTime1
        val time2M2 = dbHelper.getAllCinema().get(1).MovieStartTime2

        val rdbtnSTime1 = findViewById<RadioButton>(R.id.rdbtnSTime1)
        val rdbtnSTime2 = findViewById<RadioButton>(R.id.rdbtnSTime2)

        rdbtnMovie1.setOnClickListener {
            rdbtnSTime1.text = time1M1
            rdbtnSTime2.text = time2M1
        }

        rdbtnMovie2.setOnClickListener {
            rdbtnSTime1.text = time1M2
            rdbtnSTime2.text = time2M2
        }
    }

    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var capacity = 0

        var movieName = " "
        val rdbtnMovie1 = findViewById<RadioButton>(R.id.rdbtnMovie1)
        val rdbtnMovie2 = findViewById<RadioButton>(R.id.rdbtnMovie2)
        if(rdbtnMovie1.isChecked) {
            movieName = rdbtnMovie1.text.toString()
            capacity = dbHelper.getAllCinema().get(0).Capacity
        } else if (rdbtnMovie2.isChecked) {
            movieName = rdbtnMovie2.text.toString()
            capacity = dbHelper.getAllCinema().get(1).Capacity
        } else {
            Toast.makeText(this, "Please select a movie", Toast.LENGTH_SHORT).show()
        }

        var showTime = " "
        val rdbtnSTime1 = findViewById<RadioButton>(R.id.rdbtnSTime1)
        val rdbtnSTime2 = findViewById<RadioButton>(R.id.rdbtnSTime2)
        if(rdbtnSTime1.isChecked) {
            showTime = rdbtnSTime1.text.toString()
        } else if (rdbtnSTime2.isChecked) {
            showTime = rdbtnSTime2.text.toString()
        } else {
            Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
        }

        val dateC = findViewById<EditText>(R.id.etxtDateCinema).text.toString()

        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMovie).text.toString()
        if ((noOfpeople != "" && (noOfpeople.toInt() < capacity)) && dateC != "" &&
            (rdbtnSTime1.isChecked || rdbtnSTime2.isChecked) &&
            (rdbtnMovie1.isChecked || rdbtnMovie2.isChecked)) {

            var confirmDetails = ConfirmDetails(
                0, lastUserL.Username, lastUserL.Email,
                "Cinema", showTime, movieName, "", "",
                "", "", "", noOfpeople, dateC
            )

            if(dbHelper.addConfirmDetails(confirmDetails)) {
                Toast.makeText(this, "Booking Confirmed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ConfirmationPage::class.java))
            } else {
                Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,
                "Make sure all fields have been filled and the number of people needs to be within the capacity",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }
}