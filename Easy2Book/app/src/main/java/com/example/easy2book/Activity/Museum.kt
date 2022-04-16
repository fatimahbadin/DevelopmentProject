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
import com.example.easy2book.R

class Museum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum)

//      This is what will load on the page at the start
//      Values and variables that retrieve the dbHelper and text views
        val dbHelper = DataBaseHelper(this)

        val exhibit1 = dbHelper.getAllMuseum().get(0).Exhibitions
        val exhibit2 = dbHelper.getAllMuseum().get(1).Exhibitions
        val rbtnExhibit1 = findViewById<RadioButton>(R.id.rdbtnExhibit1)
        val rbtnExhibit2 = findViewById<RadioButton>(R.id.rdbtnExhibit2)

//      The text for the two radio buttons are set after retrieving the exhibits
//      from the museum table
        rbtnExhibit1.text = exhibit1
        rbtnExhibit2.text = exhibit2

        val time1v1 = dbHelper.getAllMuseum().get(0).VisitTime1
        val time2v1 = dbHelper.getAllMuseum().get(0).VisitTime2

        val time1v2 = dbHelper.getAllMuseum().get(1).VisitTime1
        val time2v2 = dbHelper.getAllMuseum().get(1).VisitTime2

        val rdbtnVTime1 = findViewById<RadioButton>(R.id.rdbtnVTime1)
        val rdbtnVTime2 = findViewById<RadioButton>(R.id.rdbtnVTime2)

        val price1 = dbHelper.getAllMuseum().get(0).Price
        val price2 = dbHelper.getAllMuseum().get(1).Price

        val txtPrice = findViewById<TextView>(R.id.txtPriceMuseum)

//      When either of the radio  buttons for the movies is clicked a different time
//      will be set to the text of the time radio buttons
        rbtnExhibit1.setOnClickListener {
            rdbtnVTime1.text = time1v1
            rdbtnVTime2.text = time2v1
            txtPrice.text = price1.toString()
        }

        rbtnExhibit2.setOnClickListener {
            rdbtnVTime1.text = time1v2
            rdbtnVTime2.text = time2v2
            txtPrice.text = price2.toString()
        }
    }

//  This function is for the confirm button.
//  Depending on what the user has selected for each section, the details will be added
//  to the booking details table accordingly
    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var txtPrice = 0
        val price1 = dbHelper.getAllMuseum().get(0).Price
        val price2 = dbHelper.getAllMuseum().get(1).Price

        var exhibit = " "
        val exhibit1 = findViewById<RadioButton>(R.id.rdbtnExhibit1)
        val exhibit2 = findViewById<RadioButton>(R.id.rdbtnExhibit2)
        if (exhibit1.isChecked) {
            exhibit = exhibit1.text.toString()
            txtPrice = price1
        } else if (exhibit2.isChecked) {
            exhibit = exhibit2.text.toString()
            txtPrice = price2
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

//      If all sections have been filled then the details will be added to the booking details table
        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMuseum).text.toString()
        if ((noOfpeople != "") && dateC != "" &&
            (rdbtnVTime1.isChecked || rdbtnVTime2.isChecked) &&
            (exhibit1.isChecked || exhibit2.isChecked)
        ) {

            var txtPriceUpdated = txtPrice * noOfpeople.toInt()

            var confirmDetails = ConfirmDetails(
                0, txtPriceUpdated, lastUserL.Email,"Museum", visitTime,
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
            Toast.makeText(this,
                "Make sure all fields have been filled in",
                Toast.LENGTH_SHORT).show()        }
    }

//  Function for the back button to take the user back to the home page
    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }
}