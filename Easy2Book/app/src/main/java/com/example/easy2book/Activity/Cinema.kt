package com.example.easy2book.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.easy2book.ConfirmationPage
import com.example.easy2book.Home
import com.example.easy2book.Model.ConfirmDetails
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.Nav.ActivityFragment
import com.example.easy2book.R

class Cinema : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)
    }

    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var movieName = " "
        val rdbtnMovie1 = findViewById<RadioButton>(R.id.rdbtnMovie1)
        val rdbtnMovie2 = findViewById<RadioButton>(R.id.rdbtnMovie2)
        if(rdbtnMovie1.isChecked) {
            movieName = rdbtnMovie1.text.toString()
        } else if (rdbtnMovie2.isChecked) {
            movieName = rdbtnMovie2.text.toString()
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

        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMovie).toString()
        if (noOfpeople != "" && (rdbtnSTime1.isChecked || rdbtnSTime2.isChecked)
            && (rdbtnMovie1.isChecked || rdbtnMovie2.isChecked)) {

            var confirmDetails = ConfirmDetails(0, "Cinema", showTime, movieName,
                "", "", "", "", "", noOfpeople)

            if(dbHelper.addConfirmDetails(confirmDetails)) {
                Toast.makeText(this, "Details Added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ConfirmationPage::class.java))
            } else {
                Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Make sure all fields have been filled", Toast.LENGTH_SHORT).show()
        }
    }

    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }
}