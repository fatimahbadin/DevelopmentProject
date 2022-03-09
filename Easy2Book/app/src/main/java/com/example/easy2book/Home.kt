package com.example.easy2book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.easy2book.Nav.HomeFragment
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val imgMenuToggle = findViewById<ImageView>(R.id.imgMenuToggle)
        val navView = findViewById<NavigationView>(R.id.navDrawer)
//        val btnActivity = findViewById<CardView>(R.id.btnActivity)

//        supportFragmentManager.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()

        navView.itemIconTintList = null
        imgMenuToggle.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(navView, navController)

        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            txtTitle.text = destination.label
        }
    }
}