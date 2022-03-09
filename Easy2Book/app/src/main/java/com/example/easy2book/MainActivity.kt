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

        val dbHelper = DataBaseHelper(this)
        var userList = ArrayList<User>()
        val u = dbHelper.getAllUsers()


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
            signUp.setTextColor(resources.getColor(R.color.btnColour, null))
            login.background = resources.getDrawable(R.drawable.switch_trcks)
            signUpLayout.visibility = View.GONE
            loginLayout.visibility = View.VISIBLE
            login.setTextColor(resources.getColor(R.color.textColor, null))
        }

        btnSignUp.setOnClickListener {
            val usernameInputS = findViewById<TextInputEditText>(R.id.txtSignUpUsername).text.toString()
            val emailInputS = findViewById<TextInputEditText>(R.id.txtSignUpEmail).text.toString()
            val pwInputS = findViewById<TextInputEditText>(R.id.txtSignUpPassword).text.toString()

            if (usernameInputS.isEmpty() && emailInputS.isEmpty() && pwInputS.isEmpty()) {
                Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_SHORT).show()
            } else {
                val newUser = User(0, usernameInputS, emailInputS, pwInputS)

                val result = dbHelper.addUser(newUser)

                when (result) {
                    1 -> {
                        Toast.makeText(this, "Account has been created! Please login now.", Toast.LENGTH_SHORT).show()
                        signUp.background = null
                        signUp.setTextColor(resources.getColor(R.color.btnColour,null))
                        login.background = resources.getDrawable(R.drawable.switch_trcks)
                        signUpLayout.visibility = View.GONE
                        loginLayout.visibility = View.VISIBLE
                        login.setTextColor(resources.getColor(R.color.textColor, null))
                    }
                    -3 ->
                        Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show()
                }
            }
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

        btnLogin.setOnClickListener {
            val usernameInputL = findViewById<TextInputEditText>(R.id.txtUsername).text.toString()
            val passwordInputL = findViewById<TextInputEditText>(R.id.txtPassword).text.toString()

            userList.addAll(u)

            if (usernameInputL.isNotEmpty() && passwordInputL.isNotEmpty()){
                for (i in userList) {
                    if (usernameInputL == i.Username && passwordInputL == i.Password){
                        Toast.makeText(this, "Welcome to Easy2Book $usernameInputL!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Home::class.java))
                    } else if (usernameInputL != i.Username || passwordInputL != i.Password) {
                        Toast.makeText(this, "Username or password is incorrect.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Both fields must be filled in", Toast.LENGTH_SHORT).show()
            }


//            if (usernameInputL.isEmpty() || passwordInputL.isEmpty()){
//                Toast.makeText(this, "Username and Password fields must be filled in.", Toast.LENGTH_SHORT).show()
//            } else if (dbHelper.loginValid(usernameInputL, passwordInputL)) {
//                Toast.makeText(this, "Welcome to Easy2Book $usernameInputL!", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, Home::class.java))
//            } else {
//                Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT).show()
//            }
        }

//        btnSignUp.setOnClickListener {
//            val usernameInputS = findViewById<TextInputEditText>(R.id.txtSignUpUsername).text.toString()
//            val passwordInputS = findViewById<TextInputEditText>(R.id.txtSignUpPassword).text.toString()
//            val emailInputS = findViewById<TextInputEditText>(R.id.txtSignUpEmail).text.toString()
//
//            for (i in userList) {
//                if (usernameInputS.toLowerCase() == i.Username.toLowerCase()){
//                    Toast.makeText(this, "Account exists", Toast.LENGTH_SHORT).show()
//                }
//            }
//                for (i in userList) {
//                    if (usernameInputS != "" && passwordInputS != "" && emailInputS != ""){
//                        var un = usernameInputS
//                        var pw = passwordInputS
//                        var em = emailInputS
//
//                        var user = User(0, un, pw, em)
//
//                        if (dbHelper.addUser(user)) {
//                            Toast.makeText(this, "Account has been created", Toast.LENGTH_SHORT).show()
//                            signUp.background = null
//                            signUp.setTextColor(resources.getColor(R.color.btnColour,null))
//                            login.background = resources.getDrawable(R.drawable.switch_trcks)
//                            signUpLayout.visibility = View.GONE
//                            loginLayout.visibility = View.VISIBLE
//                            login.setTextColor(resources.getColor(R.color.textColor, null))
//                        } else {
//                            Toast.makeText(this, "Account already exits", Toast.LENGTH_SHORT).show()
//                        }
 //                   }

 //       }

    }

}
