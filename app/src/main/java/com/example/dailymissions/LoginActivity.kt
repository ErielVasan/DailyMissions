package com.example.dailymissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.loginButton).setOnClickListener {

            if (findViewById<EditText>(R.id.loginUsernameInput).text.trim().isNotEmpty() &&
                findViewById<EditText>(R.id.loginPasswordInput).text.trim().isNotEmpty()
            ){

                val intent = Intent(this, MissionsActivity::class.java)
                startActivity(intent)
                finish()

            }else{

                Toast.makeText(this, "Input Missing", Toast.LENGTH_SHORT).show()

            }

        }

        findViewById<TextView>(R.id.toRegisterTextView).setOnClickListener{

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}