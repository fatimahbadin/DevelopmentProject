package com.example.easy2book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.easy2book.Model.*
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Values and variables that will be needed throughout this activity
        val signUp = findViewById<TextView>(R.id.signUp)
        val login = findViewById<TextView>(R.id.login)
        val signUpLayout = findViewById<LinearLayout>(R.id.signUpLayout)
        val loginLayout = findViewById<LinearLayout>(R.id.loginLayout)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val txtForgotPW = findViewById<TextView>(R.id.txtForgotPW)
        val usernameInputS = findViewById<TextInputEditText>(R.id.txtSignUpUsername)
        val emailInputS = findViewById<TextInputEditText>(R.id.txtSignUpEmail)
        val pwInputS = findViewById<TextInputEditText>(R.id.txtSignUpPassword)
        val usernameInputL = findViewById<TextInputEditText>(R.id.txtUsername)
        val passwordInputL = findViewById<TextInputEditText>(R.id.txtPassword)

//      Call on the DataBaseHelper
        val dbHelper = DataBaseHelper(this)

//      Function for when the signUp label is clicked,
//      the login layout will appear and sign up layout will disappear
        signUp.setOnClickListener {
            signUp.background = resources.getDrawable(R.drawable.switch_trcks)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            login.background = null
            usernameInputS.text = null
            emailInputS.text = null
            pwInputS.text = null
            signUpLayout.visibility = View.VISIBLE
            loginLayout.visibility = View.GONE
            login.setTextColor(resources.getColor(R.color.btnColour, null))
        }

//      Function for when the login label is clicked,
//      the sign up layout will appear and login will disappear
        login.setOnClickListener {
            signUp.background = null
            signUp.setTextColor(resources.getColor(R.color.btnColour, null))
            login.background = resources.getDrawable(R.drawable.switch_trcks)
            usernameInputL.text = null
            passwordInputL.text = null
            signUpLayout.visibility = View.GONE
            loginLayout.visibility = View.VISIBLE
            login.setTextColor(resources.getColor(R.color.textColor, null))
        }

//      Function for the signUp button.
//      If any fields are empty and the password is less than 8 characters, the user will be notified
//      then the users details will be checked against existing details
//      If details do not exist the users details will be saved onto the user table and they can login
        btnSignUp.setOnClickListener {

            if (usernameInputS.text.toString().lowercase().isEmpty() ||
                emailInputS.text.toString().lowercase().isEmpty() ||
                pwInputS.text.toString().lowercase().isEmpty()) {
                Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_SHORT).show()
            } else if (pwInputS.text.toString().length < 8) {
                Toast.makeText(this, "Password must be 8 or more characters long", Toast.LENGTH_SHORT).show()
            } else {
                val newUser = User(0, usernameInputS.text.toString(), pwInputS.text.toString(), emailInputS.text.toString())

                val result = dbHelper.signUpUser(newUser)

                when (result) {
                    1 -> {
                        Toast.makeText(this, "Account has been created! Please login now.", Toast.LENGTH_SHORT).show()
                        signUp.background = null
                        signUp.setTextColor(resources.getColor(R.color.btnColour,null))
                        login.background = resources.getDrawable(R.drawable.switch_trcks)
                        usernameInputS.text = null
                        emailInputS.text = null
                        pwInputS.text = null
                        signUpLayout.visibility = View.GONE
                        loginLayout.visibility = View.VISIBLE
                        login.setTextColor(resources.getColor(R.color.textColor, null))
                    }
                    -3 ->
                        Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }

//      Function for the "forgot password" label
        txtForgotPW.setOnClickListener {
            Toast.makeText(this, "Please sign up again", Toast.LENGTH_SHORT).show()
            signUp.background = resources.getDrawable(R.drawable.switch_trcks)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            login.background = null
            usernameInputL.text = null
            passwordInputL.text = null
            signUpLayout.visibility = View.VISIBLE
            loginLayout.visibility = View.GONE
            login.setTextColor(resources.getColor(R.color.btnColour, null))
        }

//      Function for the login button, the users details will be checked against existing users
//      If details are incorrect, the user will not be able to login and will be told details are incorrect
//      If details are correct, the details will be saved onto another table so we can retrieve the details
//      within the rest of the application
        btnLogin.setOnClickListener {

            var userList = ArrayList<User>()
            val u = dbHelper.getAllUsers()

            userList.addAll(u)

            var email = ""
            for (i in userList) {
                if (usernameInputL.text.toString().lowercase() == i.Username) {
                    email = i.Email
                }
            }

            if (dbHelper.loginValid(usernameInputL.text.toString().lowercase(), passwordInputL.text.toString().lowercase())) {
                var userLogged = UserLogged(0, usernameInputL.text.toString(), passwordInputL.text.toString(), email)
                if (dbHelper.addLoggedUser(userLogged)) {
                    Toast.makeText(this, "Welcome to Easy2Book ${usernameInputL.text.toString()}!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Home::class.java))
                } else {
                    Toast.makeText(this, "Not Added", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
