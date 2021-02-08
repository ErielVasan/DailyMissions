package com.example.dailymissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException


class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var emailInput: String
    private lateinit var passwordInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.loginButton).setOnClickListener {

            emailInput = findViewById<EditText>(R.id.loginEmailInput).text.trim().toString()
            passwordInput = findViewById<EditText>(R.id.loginPasswordInput).text.trim().toString()

            if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()){
                signInUser()
            } else {
                Toast.makeText(this, "Invalid information, please retry", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<TextView>(R.id.toRegisterTextView).setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signInUser() {

        firebaseAuth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MissionsActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    try {
                        throw task.exception!!
                    }
                    catch(e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this,
                            "Invalid information, please retry",
                            Toast.LENGTH_SHORT).show()
                    }
                    catch (e: Exception) {
                        Toast.makeText(this, "Unknown Registration Failure", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("TAG", e.toString())
                    }
                }
            }
    }
}