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

    lateinit var activityList : ArrayList<String>
    lateinit var transportList : ArrayList<String>
    lateinit var idList : ArrayList<Int>
    lateinit var randList : ArrayList<ConfirmDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)

        val dbHelper = DataBaseHelper(this)
        val user = dbHelper.getAllLoggedUsers().last().Username
        val bookings = dbHelper.getAllConfirmDetails()
        activityList = ArrayList()
        transportList = ArrayList()
        idList = ArrayList()
        randList = ArrayList()

        for (i in bookings) {
            if(i.Username == user){
                activityList.add(i.Activity)
                transportList.add(i.Transport)
                idList.add(i.ID)
            }
        }

        activityList.removeAll(listOf("", null))
        transportList.removeAll(listOf("", null))

        val adapter1 : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityList)
        val adapter2 : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, transportList)

        var list1 = findViewById<ListView>(R.id.listView1)
        var list2 = findViewById<ListView>(R.id.listView2)

        list1.adapter = adapter1
        list2.adapter = adapter2

        list1.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            MaterialAlertDialogBuilder(this).apply {
                setTitle("Booking Details")

                val getCurrent = dbHelper.getAllConfirmDetailsForId(idList[id.toInt()])
                for ( i in getCurrent ){
                    randList.add(i)
                }
                val current = getCurrent.get(0)

                setMessage("\nTotal Price: £${current.Price}\n" +
                        "\nActivity: ${current.Activity}\n" +
                        "\nMovie Name or Exhibition: ${current.MovieName} ${current.Exhibition}\n" +
                        "\nTime Booked: ${current.TimeBooked}\n" +
                        "\nNumber of People: ${current.NoOfPeople}\n" +
                        "\nClick 'Edit' to edit the number of people")

                setPositiveButton("Delete") {_,_ ->
                    activityList.removeAt(position)
                    dbHelper.deleteData(idList[id.toInt()])
                    adapter1.notifyDataSetChanged()
                }

                setNeutralButton("Edit") {_,_ ->
                    val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
                    builder.setTitle("Edit Number of People")
                    val txtEditNOP = EditText(context)
                    txtEditNOP.setHint("Enter number of people")
                    txtEditNOP.inputType = InputType.TYPE_CLASS_NUMBER
                    builder.setView(txtEditNOP)

                    builder.setPositiveButton("Confirm") { dialog, which ->
                        fun updateNOP(id: Int, price: Int, email: String,
                                      activity : String, timeBooked: String,
                                      movieName: String, exhibition: String,
                                      transport: String, locationFrom: String,
                                      locationTo: String, departTime: String,
                                      noOfPeople: String, date: String, username: String){
                            val newNOP = ConfirmDetails(id, price, email, activity, timeBooked,
                                movieName, exhibition, transport, locationFrom, locationTo,
                                departTime, noOfPeople, date, username)
                            dbHelper.updateNOP(newNOP)
                        }

                        var updatedPrice = (current.Price / current.NoOfPeople.toInt()) * txtEditNOP.text.toString().toInt()

                        updateNOP(current.ID, updatedPrice, current.Email, current.Activity,
                            current.TimeBooked, current.MovieName, current.Exhibition, current.Transport,
                            current.LocationFrom, current.LocationTo, current.DepartTime,
                            txtEditNOP.text.toString(), current.Date, current.Username)
                        dialog.cancel()
                    }

                    builder.setNegativeButton("Cancel") { dialog, which ->
                        dialog.cancel()
                    }

                    builder.show()

                }.create().show()
            }
        }
    }

    //  Function for the back button to take the user back to the home page
    fun backBtn (view: View) {
        startActivity(Intent(this, Home::class.java))
    }
}