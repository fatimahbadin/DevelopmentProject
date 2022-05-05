package com.example.easy2book.Transport

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
import java.text.SimpleDateFormat
import java.util.*

class Train : AppCompatActivity() {
//  variables for date picker function
    var selectDate: TextView? = null
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train)

//      This is what will load on the page at the start
//      Values and variables that retrieve the dbHelper and text views         
        val dbHelper = DataBaseHelper(this)

        val train1 = dbHelper.getAllTrain().first().LocationFrom
        val train2 = dbHelper.getAllTrain().last().LocationFrom
        val rdbtnFrom1 = findViewById<RadioButton>(R.id.rdbtnFrom1Train)
        val rdbtnFrom2 = findViewById<RadioButton>(R.id.rdbtnFrom2Train)

//      The text for the two radio buttons are set after retrieving the locatioFrom
//      from the train table
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

        val price1 = dbHelper.getAllTrain().first().Price
        val price2 = dbHelper.getAllTrain().last().Price

        val txtPrice = findViewById<TextView>(R.id.txtPriceTrain)

//      When either of the radio  buttons for the movies is clicked a different time
//      and locationTo will be set to the text of the time and locationTo radio buttons
        rdbtnFrom1.setOnClickListener {
            rdbtnArr1.text = locationTo
            rdbtnTime1Train.text = departTime1t1
            rdbtnTime2Train.text = departTime2t1
            rdbtnTime3Train.text = departTime3t1
            txtPrice.text = "Price: £" + price1.toString() + ".00 per person"
        }

        rdbtnFrom2.setOnClickListener {
            rdbtnArr1.text = locationTo
            rdbtnTime1Train.text = departTime1t2
            rdbtnTime2Train.text = departTime2t2
            rdbtnTime3Train.text = departTime3t2
            txtPrice.text = "Price: £" + price2.toString() + ".00 per person"
        }

    }

    //  This function is for the confirm button.
//  Depending on what the user has selected for each section, the details will be added
//  to the booking details table accordingly
    fun confirmBtn (view: View) {
        val dbHelper = DataBaseHelper(this)

        var txtPrice = 0
        val price1 = dbHelper.getAllTrain().first().Price
        val price2 = dbHelper.getAllTrain().last().Price

        var locationFrom = " "
        val rdbtnFrom1 = findViewById<RadioButton>(R.id.rdbtnFrom1Train)
        val rdbtnFrom2 = findViewById<RadioButton>(R.id.rdbtnFrom2Train)
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

        val dateC = findViewById<TextView>(R.id.etxtDateCinema).text.toString()
        val lastUserL = dbHelper.getAllLoggedUsers().last()
        val noOfpeople = findViewById<EditText>(R.id.etxtNoOfPeopleMovie).text.toString()

//      If all sections have been filled then the details will be added to the booking details table
        if(dateC != "Click here to select a date"){
            val format = SimpleDateFormat("dd/MM/yyyy")
            val date: Date = format.parse(dateC)
            if (date > Calendar.getInstance().time) {
                if  ((noOfpeople != "" && noOfpeople.toInt() > 0) && (dateC != "" && dateC.contains("/")) &&
                    (rdbtnTime1Train.isChecked || rdbtnTime2Train.isChecked || rdbtnTime3Train.isChecked) &&
                    (rdbtnArr1.isChecked) &&
                    (rdbtnFrom1.isChecked || rdbtnFrom2.isChecked)
                ) {

                    var txtPriceUpdated = txtPrice * noOfpeople.toInt()

                    val transport = dbHelper.getAllTransport().get(0).Transport

                    var confirmDetails = ConfirmDetails(
                        0, txtPriceUpdated, lastUserL.Email,
                        "", "", "", "", transport,
                        locationFrom, arrivalLocation, departTime, noOfpeople.toInt(), dateC, lastUserL.Username
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
        selectDate = findViewById(R.id.etxtDateTrain)
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