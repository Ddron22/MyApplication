package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val registerButton = findViewById<Button>(R.id.buttonSingIn)
        firebaseAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            val editTextEmail = findViewById<EditText>(R.id.editTextEmail).editableText.toString()
            val editTextPass = findViewById<EditText>(R.id.editTextPass).editableText.toString()
            val editTextPassCheck = findViewById<EditText>(R.id.editTextPassCheck).editableText.toString()

            if(editTextEmail.isEmpty())
                Toast.makeText(this, "Поле \"Email\" не заполнено", Toast.LENGTH_SHORT).show()
            else if(editTextPass.isEmpty())
                Toast.makeText(this, "Поле \"Пароль\" не заполнено", Toast.LENGTH_SHORT).show()
            else {
                if (editTextEmail.toString().contains("@")){
                    if(editTextPass.length <= 5){
                        Toast.makeText(this, "Пароль должен быть от шести символов", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        if (editTextPass.toString() != editTextPassCheck) {
                            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                        } else {
                            firebaseAuth.createUserWithEmailAndPassword(editTextEmail, editTextPass)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Неверный логин или пароль",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    }

                }
                else
                    Toast.makeText(this, "Email введен неверно", Toast.LENGTH_SHORT).show()
            }
        }
    }
}