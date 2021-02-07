package com.example.dailymissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailInput: String
    private lateinit var passwordInput: String
    private lateinit var passwordConfirmInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()


        findViewById<Button>(R.id.registerButton).setOnClickListener {

            emailInput = findViewById<EditText>(R.id.registerEmailInput).text.trim().toString()
            passwordInput = findViewById<EditText>(R.id.registerPasswordInput).text.trim().toString()
            passwordConfirmInput = findViewById<EditText>(R.id.registerPasswordConfirmInput).text.trim().toString()

            if (emailInput.isNotEmpty() && passwordInput.isNotEmpty() && passwordConfirmInput.isNotEmpty()) {

                if (passwordInput == passwordConfirmInput){

                    registerUser()

                } else {

                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()

                }

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

    fun registerUser(){ //  TODO: catch exceptions
        auth.createUserWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Registration Complete", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}