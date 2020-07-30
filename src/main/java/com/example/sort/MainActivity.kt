package com.example.sort

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.core.ValueEventRegistration
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import com.google.zxing.integration.android.IntentIntegrator;


class MainActivity : AppCompatActivity() {

    val niebiesk = "niebieski"
    val zolt = "zolty"
    val zielon = "zielony"
    val brazow = "brazowy"
    val czarn = "czarny"
    var sprawdzenie = 1











    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignOut.visibility = View.INVISIBLE
        Info.visibility = View.INVISIBLE


        var  user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            SignOut.visibility = View.VISIBLE
            Glogbutt.visibility = View.INVISIBLE
            Grejbutt.visibility = View.INVISIBLE
            dodaj_kod.visibility = View.VISIBLE
            Info.visibility = View.INVISIBLE
        }

        SignOut.setOnClickListener{

            FirebaseAuth.getInstance().signOut()

            Toast.makeText(this, "Pomyślnie wylogowano", Toast.LENGTH_SHORT).show()
            SignOut.visibility = View.INVISIBLE
            Glogbutt.visibility = View.VISIBLE
            Grejbutt.visibility = View.VISIBLE
            dodaj_kod.visibility = View.INVISIBLE
            Info.visibility = View.VISIBLE


        }



        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener { menuItem ->

            if (menuItem.itemId == R.id.przycisk1){
                var zasady : Intent = Intent(applicationContext, Zasady::class.java)
                startActivity(zasady)
            }
            if (menuItem.itemId == R.id.przycisk2){
                Toast.makeText(applicationContext, "Dxxxx" , Toast.LENGTH_LONG).show()
            }


            true
        }

        Glogbutt.setOnClickListener{
            var akt : Intent = Intent(applicationContext, Logowanie::class.java)
            startActivity(akt)

        }

        Grejbutt.setOnClickListener{
            var akt : Intent = Intent(applicationContext, Rejestracja::class.java)
            startActivity(akt)

        }


    skanuj_kod.setOnClickListener {

        val scanner = IntentIntegrator(this)
        sprawdzenie = 2


        scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        scanner.setBeepEnabled(false)

        scanner.initiateScan()



        }



        dodaj_kod.setOnClickListener{
            var akt : Intent = Intent(applicationContext, DodawanieKoduDoBazyActivity::class.java)
            startActivity(akt)



        }
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            var kod = result.contents.toString()

            if (sprawdzenie == 2) {
                if (result != null) {
                    sprawdzenie =1
                    if (result.contents != null) {
                        var zeskanowany_kod = result.contents.toString()

                        val ref = FirebaseDatabase.getInstance().getReference("kody")
                        ref.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(error: DatabaseError) {}

                            override fun onDataChange(snapshot: DataSnapshot) {


                                if (zeskanowany_kod in snapshot.toString()) {

                                    for (x in snapshot.children) {
                                        var odpad = x.value.toString()
                                        if (zeskanowany_kod in odpad) {
                                            if (niebiesk in odpad) {
                                                var nieb: Intent = Intent(
                                                        applicationContext,
                                                        niebieski::class.java
                                                    )
                                                startActivity(nieb)
                                            } else if (zolt in odpad) {
                                                var zolt: Intent =
                                                    Intent(applicationContext, zolty::class.java)
                                                startActivity(zolt)
                                            } else if (zielon in odpad) {
                                                var zielon: Intent =
                                                    Intent(applicationContext, zielony::class.java)
                                                startActivity(zielon)
                                            } else if (brazow in odpad) {
                                                var brazow: Intent =
                                                    Intent(applicationContext, brazowy::class.java)
                                                startActivity(brazow)
                                            } else if (czarn in odpad) {
                                                var czarn: Intent =
                                                    Intent(applicationContext, czarny::class.java)
                                                startActivity(czarn)
                                            }
                                        }
                                    }
                                } else

                                    Toast.makeText(
                                        applicationContext,
                                        "Niestety nie mamy danego produktu w bazie",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    
                            }
                        })
                    }
                }
            }
        }
    }
}

