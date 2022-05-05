package com.example.easy2book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.easy2book.Model.ConfirmDetails
import com.example.easy2book.Model.DataBaseHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BookingHistory : AppCompatActivity() {

    lateinit var bookingsList: ArrayList<String>
    lateinit var idList: ArrayList<Int>
    lateinit var randList: ArrayList<ConfirmDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)

        val dbHelper = DataBaseHelper(this)
        val user = dbHelper.getAllLoggedUsers().last().Username
        val bookings = dbHelper.getAllConfirmDetails()
        idList = ArrayList()
        randList = ArrayList()
        bookingsList = ArrayList()

        for (i in bookings) {
            if (i.Username == user && i.Activity != "") {
                idList.add(i.ID)
                bookingsList.add(i.Activity)
            } else if (i.Username == user && i.Transport != "") {
                idList.add(i.ID)
                bookingsList.add(i.Transport)
            }
        }

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookingsList)

        var listView = findViewById<ListView>(R.id.listView)

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            MaterialAlertDialogBuilder(this).apply {
                setTitle("Booking Details")

                val getCurrent = dbHelper.getAllConfirmDetailsForId(idList[id.toInt()])
                for (i in getCurrent) {
                    randList.add(i)
                }
                val current = getCurrent.get(0)

                var ActOrTrans = ""
                var Time = ""
                var MovOrEx = ""
                var LocationFrom = ""
                var LocationTo = ""
                if (current.Transport == "" && current.Activity == "Cinema") {
                    ActOrTrans = "Activity Booked: " + current.Activity
                    Time = "Time Booked: " + current.TimeBooked
                    MovOrEx = "Movie Name: " + current.MovieName
                } else if (current.Transport == "" && current.Activity == "Museum") {
                    ActOrTrans = "Activity Booked: " + current.Activity
                    Time = "Time Booked: " + current.TimeBooked
                    MovOrEx = "Exhibit Booked: " + current.Exhibition
                } else if (current.Activity == "") {
                    ActOrTrans = "Transport Booked: " + current.Transport
                    LocationFrom = "Location From: " + current.LocationFrom
                    LocationTo = "Location To: " + current.LocationTo
                    Time = "Depart Time: " + current.DepartTime
                }

                setMessage(
                    "\nTotal Price: £${current.Price}\n" +
                            "\nNumber of People: ${current.NoOfPeople}\n" +
                            "\n$ActOrTrans\n" +
                            "\n$MovOrEx\n" +
                            "\nDate: ${current.Date}\n" +
                            "\n$Time\n" +
                            "\n$LocationFrom\n" +
                            "\n$LocationTo\n" +
                            "\n\nClick 'Edit' to edit the number of people")


                setPositiveButton("Delete") { _, _ ->
                    val builder: android.app.AlertDialog.Builder =
                        android.app.AlertDialog.Builder(context)
                    builder.setTitle("Delete Booking?")
                    builder.setMessage("Are you sure you would like to delete the booking?")

                    builder.setPositiveButton("Confirm") { dialog, which ->
                        bookingsList.removeAt(position)
                        dbHelper.deleteData(idList[id.toInt()])
                        adapter.notifyDataSetChanged()
                        dialog.cancel()
                    }

                    builder.setNegativeButton("Cancel") { dialog, which ->
                        dialog.cancel()
                    }

                    builder.show()
                }

                setNegativeButton("Edit") { _, _ ->
                    val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
                    builder.setTitle("Edit Number of People")
                    val txtEditNOP = EditText(context)
                    txtEditNOP.setHint("Enter number of people")
                    txtEditNOP.inputType = InputType.TYPE_CLASS_NUMBER
                    builder.setView(txtEditNOP)

                    builder.setPositiveButton("Confirm") { dialog, which ->
                        var priceDivide = current.Price / current.NoOfPeople
                        var updatedNOP = txtEditNOP.text.toString()
                        var updatedPrice = priceDivide * updatedNOP.toInt()
                        fun updateNOP(
                            id: Int, price: Int, email: String,
                            activity: String, timeBooked: String,
                            movieName: String, exhibition: String,
                            transport: String, locationFrom: String,
                            locationTo: String, departTime: String,
                            noOfPeople: Int, date: String, username: String) {
                            val newNOP = ConfirmDetails(
                                id, price, email, activity, timeBooked,
                                movieName, exhibition, transport, locationFrom, locationTo,
                                departTime, noOfPeople, date, username)
                            dbHelper.updateNOP(newNOP)
                        }

                        updateNOP(current.ID, updatedPrice, current.Email,
                            current.Activity, current.TimeBooked, current.MovieName,
                            current.Exhibition, current.Transport, current.LocationFrom,
                            current.LocationTo, current.DepartTime, txtEditNOP.text.toString().toInt(),
                            current.Date, current.Username)
                        dialog.cancel()
                    }

                    builder.setNegativeButton("Cancel") { dialog, which ->
                        dialog.cancel()
                    }

                    builder.show()

                }

                setNeutralButton("Cancel") { _, _ ->

                }.create().show()
            }
        }
    }

        //  Function for the back button to take the user back to the home page
      fun backBtn(view: View) {
            startActivity(Intent(this, Home::class.java))
      }
}