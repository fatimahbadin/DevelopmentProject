package com.example.easy2book.Activity

import android.app.AlertDialog
import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.*

class Cinema : AppCompatActivity() {
//  variables for date picker function
    var selectDate: TextView? = null
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
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
        if (rdbtnMovie1.isChecked) {
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
        if (rdbtnSTime1.isChecked) {
            showTime = rdbtnSTime1.text.toString()
        } else if (rdbtnSTime2.isChecked) {
            showTime = rdbtnSTime2.text.toString()
        } else {
            Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
        }

        val dateC = findViewById<TextView>(R.id.etxtDateCinema).text.toString()
        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMovie).text.toString()

//      If all sections have been filled then the details will be added to the booking details table
        if(dateC != "Click here to select a date"){
            val format = SimpleDateFormat("dd/MM/yyyy")
            val date: Date = format.parse(dateC)
            if (date > Calendar.getInstance().time) {
                if ((noOfpeople != "" && noOfpeople.toInt() > 0) && (dateC != "Click here to select a date") &&
                    (rdbtnSTime1.isChecked || rdbtnSTime2.isChecked) &&
                    (rdbtnMovie1.isChecked || rdbtnMovie2.isChecked)
                ) {

                    var txtPriceUpdated = txtPrice * noOfpeople.toInt()

                    val activity = dbHelper.getAllActivity().get(0).Activity

                    var confirmDetails = ConfirmDetails(
                        0, txtPriceUpdated, lastUserL.Email,
                        activity, showTime, movieName, "", "",
                        "", "", "", noOfpeople.toInt(), dateC, lastUserL.Username
                    )

                    if (dbHelper.addConfirmDetails(confirmDetails)) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                        builder.setTitle("Total Price")
                        builder.setMessage(
                            "The total price is:  £$txtPriceUpdated\n" +
                                    "\nPayment will be made when you arrive. " +
                                    "\nPlease confirm you would like to book."
                        )

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
                    Toast.makeText(
                        this,
                        "Make sure all fields have been filled in and you have more than 0 people booked",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            else{
                Toast.makeText(
                    this,
                    "Please enter a valid date",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else{
            Toast.makeText(
                this,
                "Please enter a valid date and make sure all fields are filled in",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

//  Function to display the calender when the user clicks the text view
    fun datePicker(view: View) {
        selectDate = findViewById(R.id.etxtDateCinema)
        val calender = Calendar.getInstance()
        mYear = calender[Calendar.YEAR]
        mMonth = calender[Calendar.MONTH]
        mDay = calender[Calendar.DAY_OF_MONTH]

        //Show dialog
        val datePickerDialog = DatePickerDialog(
            this, {
                view, year, month, day -> selectDate!!.setText(day.toString() + "/" + (month + 1) + "/" + year)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

//  Function for the back button to take the user back to the home page
    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }
}