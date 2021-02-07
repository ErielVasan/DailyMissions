package com.example.dailymissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.registerButton).setOnClickListener {

            if (findViewById<EditText>(R.id.registerUsernameInput).text.trim().isNotEmpty() &&
                findViewById<EditText>(R.id.registerPasswordInput).text.trim().isNotEmpty() &&
                findViewById<EditText>(R.id.registerPasswordConfirmInput).text.trim().isNotEmpty()
            ) {

                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()

            } else {

                Toast.makeText(this, "Input Missing", Toast.LENGTH_SHORT).show()

            }
        }

        findViewById<TextView>(R.id.toLoginTextView).setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}