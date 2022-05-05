package com.example.easy2book.Activity

import android.app.AlertDialog
import android.app.DatePickerDialog
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
import java.util.*

class Museum : AppCompatActivity() {
//  variables for date picker function
    var selectDate: TextView? = null
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
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
            txtPrice.text = "Price: £" + price1.toString() + ".00 per person"
        }

        rbtnExhibit2.setOnClickListener {
            rdbtnVTime1.text = time1v2
            rdbtnVTime2.text = time2v2
            txtPrice.text = "Price: £" + price2.toString() + ".00 per person"
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

        val dateC = findViewById<TextView>(R.id.etxtDateMuseum).text.toString()

//      If all sections have been filled then the details will be added to the booking details table
        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMuseum).text.toString()
        if ((noOfpeople != "" && noOfpeople.toInt() > 0) && (dateC != "" && dateC.contains("/")) &&
            (rdbtnVTime1.isChecked || rdbtnVTime2.isChecked) &&
            (exhibit1.isChecked || exhibit2.isChecked)
        ) {

            var txtPriceUpdated = txtPrice * noOfpeople.toInt()

            var confirmDetails = ConfirmDetails(
                0, txtPriceUpdated, lastUserL.Email,"Museum", visitTime,
                "", exhibit, "", "", "",
                "", noOfpeople.toInt(), dateC, lastUserL.Username
            )

            if (dbHelper.addConfirmDetails(confirmDetails)) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Total Price")
                builder.setMessage("The total price is:  £$txtPriceUpdated" +
                        "\nPayment will be made when you arrive. " +
                        "\nPlease confirm you would like to book.")

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
                "Make sure all fields have been filled in and you have more than 0 people booked",
                Toast.LENGTH_SHORT).show()
        }
    }

//  Function to display the calender when the user clicks the text view
    fun datePicker(view: View) {
        selectDate = findViewById(R.id.etxtDateMuseum)
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