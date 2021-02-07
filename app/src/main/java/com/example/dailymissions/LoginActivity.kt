package com.example.dailymissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.registerButton).setOnClickListener {

            if (findViewById<EditText>(R.id.registerUsernameInput).text.trim().isNotEmpty() &&
                findViewById<EditText>(R.id.registerPasswordInput).text.trim().isNotEmpty()
            ){

                val intent = Intent(this, MissionsActivity::class.java)
                startActivity(intent)
                finish()

            }else{

                Toast.makeText(this, "Input Missing", Toast.LENGTH_SHORT).show()

            }

        }

    }
}