package com.example.easy2book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.easy2book.Model.DataBaseHelper
import com.example.easy2book.Nav.HomeFragment
import com.example.easy2book.Nav.ProfileFragment
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//      This code allows for the navigation drawer to function
        //activity_home.xml page
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        //the hamburger toggle
        val imgMenuToggle = findViewById<ImageView>(R.id.imgMenuToggle)
        //the nav drawer
        val navView = findViewById<NavigationView>(R.id.navDrawer)

        //allows the nav to open
        navView.itemIconTintList = null
        imgMenuToggle.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        //changes the content within the fragment container
        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(navView, navController)

    }
}