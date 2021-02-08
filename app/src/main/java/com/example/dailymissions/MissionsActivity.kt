package com.example.dailymissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MissionsActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missions)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        firebaseAuth.signOut()
        Toast.makeText(this, "You are signed out!", Toast.LENGTH_LONG).show()

    }
}