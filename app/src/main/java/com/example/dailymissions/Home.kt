package com.example.dailymissions

import android.app.Application
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class Home : Application() {

    override fun onCreate() {
        super.onCreate()

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null){
            val intent = Intent(this, MissionsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}