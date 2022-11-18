package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textEmail = findViewById<EditText>(R.id.editTextEmail)
        val textPass = findViewById<EditText>(R.id.editTextPass)

        val button = findViewById<Button>(R.id.buttonSingIn)

        val firebaseAuth = FirebaseAuth.getInstance()

        var errorText = findViewById<TextView>(R.id.textViewError)


        button.setOnClickListener {
            if(textEmail.text.toString() == "")
                errorText.text = "Поле \"Email\" не заполнено"
            else if(textPass.text.toString() == "")
                errorText.text = "Поле \"Пароль\" не заполнено"
            else {
                if (textEmail.text.toString().contains("@")) {
                    firebaseAuth.signInWithEmailAndPassword(
                        textEmail.text.toString(),
                        textPass.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                        else
                            errorText.text = "Неверный логин или пароль"
                    }
                }
                else
                    errorText.text = "Email введен неверно"
            }
        }
        val textViewRegister = findViewById<TextView>(R.id.textViewLoginRegister)
        textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}