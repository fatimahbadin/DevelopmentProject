package com.example.easy2book.Activity

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

class Museum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum)

        val dbHelper = DataBaseHelper(this)

        val exhibit1 = dbHelper.getAllMuseum().get(0).Exhibitions
        val exhibit2 = dbHelper.getAllMuseum().get(1).Exhibitions
        val rbtnExhibit1 = findViewById<RadioButton>(R.id.rdbtnExhibit1)
        val rbtnExhibit2 = findViewById<RadioButton>(R.id.rdbtnExhibit2)

        rbtnExhibit1.text = exhibit1
        rbtnExhibit2.text = exhibit2

        val time1v1 = dbHelper.getAllMuseum().get(0).VisitTime1
        val time2v1 = dbHelper.getAllMuseum().get(0).VisitTime2

        val time1v2 = dbHelper.getAllMuseum().get(1).VisitTime1
        val time2v2 = dbHelper.getAllMuseum().get(1).VisitTime2

        val rdbtnVTime1 = findViewById<RadioButton>(R.id.rdbtnVTime1)
        val rdbtnVTime2 = findViewById<RadioButton>(R.id.rdbtnVTime2)

        rbtnExhibit1.setOnClickListener {
            rdbtnVTime1.text = time1v1
            rdbtnVTime2.text = time2v1
        }

        rbtnExhibit2.setOnClickListener {
            rdbtnVTime1.text = time1v2
            rdbtnVTime2.text = time2v2
        }
    }

    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var exhibit = " "
        val exhibit1 = findViewById<RadioButton>(R.id.rdbtnExhibit1)
        val exhibit2 = findViewById<RadioButton>(R.id.rdbtnExhibit2)
        if (exhibit1.isChecked) {
            exhibit = exhibit1.text.toString()
        } else if (exhibit2.isChecked) {
            exhibit = exhibit2.text.toString()
        } else {
            Toast.makeText(this, "Please select an exhibit", Toast.LENGTH_SHORT).show()
        }

        var visitTime = " "
        val rdbtnVTime1 = findViewById<RadioButton>(R.id.rdbtnVTime1)
        val rdbtnVTime2 = findViewById<RadioButton>(R.id.rdbtnVTime2)
        if (rdbtnVTime1.isChecked) {
            visitTime = rdbtnVTime1.text.toString()
        } else if (rdbtnVTime2.isChecked) {
            visitTime = rdbtnVTime2.text.toString()
        } else {
            Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
        }

        val dateC = findViewById<EditText>(R.id.etxtDateMuseum).text.toString()

        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMuseum).text.toString()
        if (noOfpeople != "" && dateC != "" &&
            (rdbtnVTime1.isChecked || rdbtnVTime2.isChecked) &&
            (exhibit1.isChecked || exhibit2.isChecked)
        ) {

            var confirmDetails = ConfirmDetails(
                0, lastUserL.Username, lastUserL.Email,"Museum", visitTime,
                "", exhibit, "", "", "",
                "", noOfpeople, dateC
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