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
    }

    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var locationFrom = " "
        val rdbtnBham = findViewById<RadioButton>(R.id.rdbtnBham)
        val rdbtnLough = findViewById<RadioButton>(R.id.rdbtnLough)
        if (rdbtnBham.isChecked) {
            locationFrom = rdbtnBham.text.toString()
        } else if (rdbtnLough.isChecked) {
            locationFrom = rdbtnLough.text.toString()
        } else {
            Toast.makeText(this, "Please select a location to leave from", Toast.LENGTH_SHORT)
                .show()
        }

        var arrivalLocation = " "
        val rdbtnArrLeicester = findViewById<RadioButton>(R.id.rdbtnArrLeicester)
        if (rdbtnArrLeicester.isChecked) {
            arrivalLocation = rdbtnArrLeicester.text.toString()
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
            rdbtnArrLeicester.isChecked &&
            (rdbtnBham.isChecked || rdbtnLough.isChecked)
        ) {

            var confirmDetails = ConfirmDetails(
                0, lastUserL.Username, lastUserL.Email,"", "",
                "", "", "Train", locationFrom,
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