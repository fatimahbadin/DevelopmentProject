package com.example.easy2book.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.easy2book.ConfirmationPage
import com.example.easy2book.Home
import com.example.easy2book.Model.ConfirmDetails
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.R

class Train : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train)

        val dbHelper = DataBaseHelper(this)

        val train1 = dbHelper.getAllTrain().first().LocationFrom
        val train2 = dbHelper.getAllTrain().last().LocationFrom
        val rdbtnFrom1 = findViewById<RadioButton>(R.id.rdbtnFrom1Train)
        val rdbtnFrom2 = findViewById<RadioButton>(R.id.rdbtnFrom2Train)

        rdbtnFrom1.text = train1
        rdbtnFrom2.text = train2

        val locationTo = dbHelper.getAllTrain().get(0).LocationTo

        val rdbtnArr1 = findViewById<RadioButton>(R.id.rdbtnArr1Train)

        val departTime1t1 = dbHelper.getAllTrain().first().DepartTime1
        val departTime2t1 = dbHelper.getAllTrain().first().DepartTime2
        val departTime3t1 = dbHelper.getAllTrain().first().DepartTime3

        val departTime1t2 = dbHelper.getAllTrain().last().DepartTime1
        val departTime2t2 = dbHelper.getAllTrain().last().DepartTime2
        val departTime3t2 = dbHelper.getAllTrain().last().DepartTime3

        val rdbtnTime1Train = findViewById<RadioButton>(R.id.rdbtnTime1Train)
        val rdbtnTime2Train = findViewById<RadioButton>(R.id.rdbtnTime2Train)
        val rdbtnTime3Train = findViewById<RadioButton>(R.id.rdbtnTime3Train)

        rdbtnFrom1.setOnClickListener {
            rdbtnArr1.text = locationTo
            rdbtnTime1Train.text = departTime1t1
            rdbtnTime2Train.text = departTime2t1
            rdbtnTime3Train.text = departTime3t1
        }

        rdbtnFrom2.setOnClickListener {
            rdbtnArr1.text = locationTo
            rdbtnTime1Train.text = departTime1t2
            rdbtnTime2Train.text = departTime2t2
            rdbtnTime3Train.text = departTime3t2
        }

    }

    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var locationFrom = " "
        val rdbtnFrom1 = findViewById<RadioButton>(R.id.rdbtnFrom1Train)
        val rdbtnFrom2 = findViewById<RadioButton>(R.id.rdbtnFrom2Train)
        if (rdbtnFrom1.isChecked) {
            locationFrom = rdbtnFrom1.text.toString()
        } else if (rdbtnFrom2.isChecked) {
            locationFrom = rdbtnFrom2.text.toString()
        } else {
            Toast.makeText(this, "Please select a location to leave from", Toast.LENGTH_SHORT)
                .show()
        }

        var arrivalLocation = " "
        val rdbtnArr1 = findViewById<RadioButton>(R.id.rdbtnArr1Train)
        if (rdbtnArr1.isChecked) {
            arrivalLocation = rdbtnArr1.text.toString()
        } else {
            Toast.makeText(this, "Please select a location to arrive at", Toast.LENGTH_SHORT).show()
        }

        var departTime = " "
        val rdbtnTime1Train = findViewById<RadioButton>(R.id.rdbtnTime1Train)
        val rdbtnTime2Train = findViewById<RadioButton>(R.id.rdbtnTime2Train)
        val rdbtnTime3Train = findViewById<RadioButton>(R.id.rdbtnTime3Train)
        if (rdbtnTime1Train.isChecked) {
            departTime = rdbtnTime1Train.text.toString()
        } else if (rdbtnTime2Train.isChecked) {
            departTime = rdbtnTime2Train.text.toString()
        } else if (rdbtnTime3Train.isChecked) {
            departTime = rdbtnTime3Train.text.toString()
        } else {
            Toast.makeText(this, "Please select a depart time", Toast.LENGTH_SHORT).show()
        }

        val dateC = findViewById<EditText>(R.id.etxtDateTrain).text.toString()

        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleTrain).text.toString()
        if  (noOfpeople != "" && dateC != "" &&
            (rdbtnTime1Train.isChecked || rdbtnTime2Train.isChecked || rdbtnTime3Train.isChecked) &&
            rdbtnArr1.isChecked && (rdbtnFrom1.isChecked || rdbtnFrom2.isChecked)
        ) {

            var confirmDetails = ConfirmDetails(
                0, lastUserL.Username, lastUserL.Email,"", "",
                "", "", "Train", locationFrom,
                arrivalLocation, departTime, noOfpeople, dateC
            )

            if (dbHelper.addConfirmDetails(confirmDetails)) {
                Toast.makeText(this, "Booking Confirmed", Toast.LENGTH_SHORT).show()
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