package com.example.easy2book.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.easy2book.Model.ConfirmDetails
import com.example.easy2book.Model.DataBaseHelper
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

        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMovie).toString().toInt()

        var confirmDetails = ConfirmDetails(0, "Cinema", showTime, movieName,
            "", "", "", "", "", noOfpeople)

        if(dbHelper.addConfirmDetails(confirmDetails)) {
            Toast.makeText(this, "Details Added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}