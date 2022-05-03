package com.example.easy2book.Transport

import android.app.AlertDialog
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

class Bus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

//      This is what will load on the page at the start
//      Values and variables that retrieve the dbHelper and text views
        val dbHelper = DataBaseHelper(this)

        val bus1 = dbHelper.getAllBus().first().LocationFrom
        val bus2 = dbHelper.getAllBus().last().LocationFrom
        val rdbtnFrom1 = findViewById<RadioButton>(R.id.rdbtnFrom1Bus)
        val rdbtnFrom2 = findViewById<RadioButton>(R.id.rdbtnFrom2Bus)

//      The text for the two radio buttons are set after retrieving the locatioFrom
//      from the bus table
        rdbtnFrom1.text = bus1
        rdbtnFrom2.text = bus2

        val locationTo1f1 = dbHelper.getAllBus().first().LocationTo1
        val locationTo2f1 = dbHelper.getAllBus().first().LocationTo2

        val locationTo1f2 = dbHelper.getAllBus().last().LocationTo1
        val locationTo2f2 = dbHelper.getAllBus().last().LocationTo2

        val rdbtnArr1 = findViewById<RadioButton>(R.id.rdbtnArr1Bus)
        val rdbtnArr2 = findViewById<RadioButton>(R.id.rdbtnArr2Bus)

        val departTime1b1 = dbHelper.getAllBus().first().DepartTime1
        val departTime2b1 = dbHelper.getAllBus().first().DepartTime2
        val departTime3b1 = dbHelper.getAllBus().first().DepartTime3

        val departTime1b2 = dbHelper.getAllBus().last().DepartTime1
        val departTime2b2 = dbHelper.getAllBus().last().DepartTime2
        val departTime3b2 = dbHelper.getAllBus().last().DepartTime3

        val rdbtnTime1Bus = findViewById<RadioButton>(R.id.rdbtnTime1Bus)
        val rdbtnTime2Bus = findViewById<RadioButton>(R.id.rdbtnTime2Bus)
        val rdbtnTime3Bus = findViewById<RadioButton>(R.id.rdbtnTime3Bus)

        val price1 = dbHelper.getAllBus().first().Price
        val price2 = dbHelper.getAllBus().last().Price

        val txtPrice = findViewById<TextView>(R.id.txtPriceBus)

//      When either of the radio  buttons for the movies is clicked a different time
//      and locationTo will be set to the text of the time and locationTo radio buttons
        rdbtnFrom1.setOnClickListener {
            rdbtnArr1.text = locationTo1f1
            rdbtnArr2.text = locationTo2f1
            rdbtnTime1Bus.text = departTime1b1
            rdbtnTime2Bus.text = departTime2b1
            rdbtnTime3Bus.text = departTime3b1
            txtPrice.text = "Price: £" + price1.toString() + ".00 per person"
        }

        rdbtnFrom2.setOnClickListener {
            rdbtnArr1.text = locationTo1f2
            rdbtnArr2.text = locationTo2f2
            rdbtnTime1Bus.text = departTime1b2
            rdbtnTime2Bus.text = departTime2b2
            rdbtnTime3Bus.text = departTime3b2
            txtPrice.text = "Price: £" + price2.toString() + ".00 per person"
        }
    }

    //  This function is for the confirm button.
//  Depending on what the user has selected for each section, the details will be added
//  to the booking details table accordingly
    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var txtPrice = 0
        val price1 = dbHelper.getAllBus().first().Price
        val price2 = dbHelper.getAllBus().last().Price

        var locationFrom = " "
        val rdbtnFrom1 = findViewById<RadioButton>(R.id.rdbtnFrom1Bus)
        val rdbtnFrom2 = findViewById<RadioButton>(R.id.rdbtnFrom2Bus)
        if (rdbtnFrom1.isChecked) {
            locationFrom = rdbtnFrom1.text.toString()
            txtPrice = price1
        } else if (rdbtnFrom2.isChecked) {
            locationFrom = rdbtnFrom2.text.toString()
            txtPrice = price2
        } else {
            Toast.makeText(this, "Please select a location to leave from", Toast.LENGTH_SHORT)
                .show()
        }

        var arrivalLocation = " "
        val rdbtnArr1 = findViewById<RadioButton>(R.id.rdbtnArr1Bus)
        val rdbtnArr2 = findViewById<RadioButton>(R.id.rdbtnArr2Bus)
        if (rdbtnArr1.isChecked) {
            arrivalLocation = rdbtnArr1.text.toString()
        } else if (rdbtnArr2.isChecked) {
            arrivalLocation = rdbtnArr2.text.toString()
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

//      If all sections have been filled then the details will be added to the booking details table
        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleBus).text.toString()
        if  ((noOfpeople != "") && dateC != "" &&
            (rdbtnTime1Bus.isChecked || rdbtnTime2Bus.isChecked || rdbtnTime3Bus.isChecked) &&
            (rdbtnArr1.isChecked || rdbtnArr2.isChecked) &&
            (rdbtnFrom1.isChecked || rdbtnFrom2.isChecked)
        ) {

            var txtPriceUpdated = txtPrice * noOfpeople.toInt()

            var confirmDetails = ConfirmDetails(
                0, txtPriceUpdated, lastUserL.Email, "", "",
                "", "", "Bus", locationFrom,
                arrivalLocation, departTime, noOfpeople, dateC, lastUserL.Username
            )

            if (dbHelper.addConfirmDetails(confirmDetails)) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Total Price: £$txtPriceUpdated")

                builder.setPositiveButton("Confirm") { dialog, which ->
                    Toast.makeText(this, "Booking Confirmed", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ConfirmationPage::class.java))
                }

                builder.setNegativeButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }

                builder.show()
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