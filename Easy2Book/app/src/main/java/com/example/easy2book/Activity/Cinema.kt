package com.example.easy2book.Activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.easy2book.ConfirmationPage
import com.example.easy2book.Home
import com.example.easy2book.MainActivity
import com.example.easy2book.Model.ConfirmDetails
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.R

class Cinema : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)

//      This is what will load on the page at the start
//      Values and variables that retrieve the dbHelper and text views
        val dbHelper = DataBaseHelper(this)

        val movieName1 = dbHelper.getAllCinema().get(0).MovieName
        val movieName2 = dbHelper.getAllCinema().get(1).MovieName
        val rdbtnMovie1 = findViewById<RadioButton>(R.id.rdbtnMovie1)
        val rdbtnMovie2 = findViewById<RadioButton>(R.id.rdbtnMovie2)

//      The text for the two radio buttons are set after retrieving the name of
//      the movies from the cinema table
        rdbtnMovie1.text = movieName1
        rdbtnMovie2.text = movieName2

        val time1M1 = dbHelper.getAllCinema().get(0).MovieStartTime1
        val time2M1 = dbHelper.getAllCinema().get(0).MovieStartTime2

        val time1M2 = dbHelper.getAllCinema().get(1).MovieStartTime1
        val time2M2 = dbHelper.getAllCinema().get(1).MovieStartTime2

        val rdbtnSTime1 = findViewById<RadioButton>(R.id.rdbtnSTime1)
        val rdbtnSTime2 = findViewById<RadioButton>(R.id.rdbtnSTime2)

        val price1 = dbHelper.getAllCinema().get(0).Price
        val price2 = dbHelper.getAllCinema().get(1).Price

        val txtPrice = findViewById<TextView>(R.id.txtPriceCinema)

//      When either of the radio  buttons for the movies is clicked a different time
//      will be set to the text of the time radio buttons
        rdbtnMovie1.setOnClickListener {
            rdbtnSTime1.text = time1M1
            rdbtnSTime2.text = time2M1
            txtPrice.text = "Price: £" + price1.toString() + ".00 per person"
        }

        rdbtnMovie2.setOnClickListener {
            rdbtnSTime1.text = time1M2
            rdbtnSTime2.text = time2M2
            txtPrice.text = "Price: £" + price2.toString() + ".00 per person"
        }
    }

//  This function is for the confirm button.
//  Depending on what the user has selected for each section, the details will be added
//  to the booking details table accordingly
    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var txtPrice = 0
        val price1 = dbHelper.getAllCinema().get(0).Price
        val price2 = dbHelper.getAllCinema().get(1).Price

        var movieName = " "
        val rdbtnMovie1 = findViewById<RadioButton>(R.id.rdbtnMovie1)
        val rdbtnMovie2 = findViewById<RadioButton>(R.id.rdbtnMovie2)
        if(rdbtnMovie1.isChecked) {
            movieName = rdbtnMovie1.text.toString()
            txtPrice = price1
        } else if (rdbtnMovie2.isChecked) {
            movieName = rdbtnMovie2.text.toString()
            txtPrice = price2
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

//      If all sections have been filled then the details will be added to the booking details table
        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMovie).text.toString()
        if ((noOfpeople != "") && dateC != "" &&
            (rdbtnSTime1.isChecked || rdbtnSTime2.isChecked) &&
            (rdbtnMovie1.isChecked || rdbtnMovie2.isChecked)
        ) {

            var txtPriceUpdated = txtPrice * noOfpeople.toInt()

            var confirmDetails = ConfirmDetails(
                0, txtPriceUpdated, lastUserL.Email,
                "Cinema", showTime, movieName, "", "",
                "", "", "", noOfpeople, dateC
            )

            if(dbHelper.addConfirmDetails(confirmDetails)) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Total Price: £$txtPriceUpdated")

                builder.setPositiveButton("Confirm") { dialog, which ->
                    Toast.makeText(this, "Booking Confirmed", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ConfirmationPage::class.java))
                }

                builder.setNegativeButton("Cancel") { dialog, whihc ->
                    dialog.cancel()
                }

                builder.show()
            } else {
                Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,
                "Make sure all fields have been filled in",
                Toast.LENGTH_SHORT).show()
        }
    }

//  Function for the back button to take the user back to the home page
    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }
}