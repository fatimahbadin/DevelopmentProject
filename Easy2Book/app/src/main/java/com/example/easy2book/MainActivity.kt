package com.example.easy2book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.easy2book.Model.App
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.Model.User
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUp = findViewById<TextView>(R.id.signUp)
        val login = findViewById<TextView>(R.id.login)
        val signUpLayout = findViewById<LinearLayout>(R.id.signUpLayout)
        val loginLayout = findViewById<LinearLayout>(R.id.loginLayout)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val txtForgotPW = findViewById<TextView>(R.id.txtForgotPW)

        val myApp : App
        val dbHelper = DataBaseHelper(this)
        val users = dbHelper.getAllUsers()


        myApp = App(this)
        val usersArray: ArrayList<User>
        usersArray = myApp.get_UserList()

        val usernameInputL = findViewById<TextInputEditText>(R.id.txtUsername).toString()
        val passwordInputL = findViewById<TextInputEditText>(R.id.txtPassword).toString()

        signUp.setOnClickListener {
            signUp.background = resources.getDrawable(R.drawable.switch_trcks)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            login.background = null
            signUpLayout.visibility = View.VISIBLE
            loginLayout.visibility = View.GONE
            login.setTextColor(resources.getColor(R.color.btnColour, null))
        }

        login.setOnClickListener {
            signUp.background = null
            signUp.setTextColor(resources.getColor(R.color.btnColour,null))
            login.background = resources.getDrawable(R.drawable.switch_trcks)
            signUpLayout.visibility = View.GONE
            loginLayout.visibility = View.VISIBLE
            login.setTextColor(resources.getColor(R.color.textColor, null))
        }

        btnLogin.setOnClickListener {
//            for (i in usersArray) {
//                if (i.Username == usernameInputL && i.Password == passwordInputL) {
                    startActivity(Intent(this, Home::class.java))
//                } else {
//                    Toast.makeText(this, "Incorrect username and password", Toast.LENGTH_SHORT).show()
//                }
//            }
        }

        btnSignUp.setOnClickListener {
            signUp.background = null
            signUp.setTextColor(resources.getColor(R.color.btnColour,null))
            login.background = resources.getDrawable(R.drawable.switch_trcks)
            signUpLayout.visibility = View.GONE
            loginLayout.visibility = View.VISIBLE
            login.setTextColor(resources.getColor(R.color.textColor, null))
        }

        txtForgotPW.setOnClickListener {
            Toast.makeText(this, "Please sign up again", Toast.LENGTH_SHORT).show()
            signUp.background = resources.getDrawable(R.drawable.switch_trcks)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            login.background = null
            signUpLayout.visibility = View.VISIBLE
            loginLayout.visibility = View.GONE
            login.setTextColor(resources.getColor(R.color.btnColour, null))
        }

    }
}