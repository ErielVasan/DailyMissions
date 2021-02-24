package com.example.dailymissions

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class MissionsActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missions)

        val firebaseUser = firebaseAuth.currentUser?.email.toString()
        var accountNameView = findViewById<TextView>(R.id.accountName)

//        missionItems = TODO() Get from firebase

        accountNameView.text = firebaseUser

        findViewById<Button>(R.id.signOutButton).setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<FloatingActionButton>(R.id.addMissionFloatingActionButton).setOnClickListener {
            val intent = Intent(this, AddMissionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onBackPressed() {
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }
}