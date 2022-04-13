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

class Bus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)
    }

    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var locationFrom = " "
        val rdbtnCity = findViewById<RadioButton>(R.id.rdbtnCity)
        val rdbtnFosse = findViewById<RadioButton>(R.id.rdbtnFosse)
        if (rdbtnCity.isChecked) {
            locationFrom = rdbtnCity.text.toString()
        } else if (rdbtnFosse.isChecked) {
            locationFrom = rdbtnFosse.text.toString()
        } else {
            Toast.makeText(this, "Please select a location to leave from", Toast.LENGTH_SHORT)
                .show()
        }

        var arrivalLocation = " "
        val rdbtnArrCinema = findViewById<RadioButton>(R.id.rdbtnArrCinema)
        val rdbtnArrMuseum = findViewById<RadioButton>(R.id.rdbtnArrMuseum)
        if (rdbtnArrCinema.isChecked) {
            arrivalLocation = rdbtnArrCinema.text.toString()
        } else if (rdbtnArrMuseum.isChecked) {
            arrivalLocation = rdbtnArrMuseum.text.toString()
        } else {
            Toast.makeText(this, "Please select a location to arrive at", Toast.LENGTH_SHORT).show()
        }

        var departTime = " "
        val rdbtnTime1Bus = findViewById<RadioButton>(R.id.rdbtnTime1Bus)
        val rdbtnTime2Bus = findViewById<RadioButton>(R.id.rdbtnTime2Bus)
        val rdbtnTime3Bus = findViewById<RadioButton>(R.id.rdbtnTime3Bus)
        if (rdbtnTime1Bus.isChecked) {
            departTime = rdbtnTime1Bus.text.toString()
        } else if (rdbtnTime2Bus.isChecked) {
            departTime = rdbtnTime2Bus.text.toString()
        } else if (rdbtnTime3Bus.isChecked) {
            departTime = rdbtnTime3Bus.text.toString()
        } else {
            Toast.makeText(this, "Please select a depart time", Toast.LENGTH_SHORT).show()
        }

        val dateC = findViewById<EditText>(R.id.etxtDateBus).text.toString()

        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleBus).text.toString()
        if  (noOfpeople != "" && dateC != "" &&
            (rdbtnTime1Bus.isChecked || rdbtnTime2Bus.isChecked || rdbtnTime3Bus.isChecked) &&
            (rdbtnArrCinema.isChecked || rdbtnArrMuseum.isChecked) &&
            (rdbtnCity.isChecked || rdbtnFosse.isChecked)
        ) {

            var confirmDetails = ConfirmDetails(
                0, lastUserL.Username, lastUserL.Email, "", "",
                "", "", "Bus", locationFrom,
                arrivalLocation, departTime, noOfpeople, dateC
            )

            if (dbHelper.addConfirmDetails(confirmDetails)) {
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