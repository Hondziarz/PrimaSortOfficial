package com.example.sort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class Skanowanie_dodawanie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skanowanie_dodawanie)

        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener { menuItem ->

            if (menuItem.itemId == R.id.przycisk1){
                var zasady : Intent = Intent(applicationContext, zasady_sortowania_odpadow::class.java)
                startActivity(zasady)
            }
             if (menuItem.itemId == R.id.przycisk2){
                Toast.makeText(applicationContext, "Dxxxx" , Toast.LENGTH_LONG).show()
            }


            true
        }

    }
}