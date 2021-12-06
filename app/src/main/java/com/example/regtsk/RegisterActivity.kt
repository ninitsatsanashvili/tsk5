package com.example.regtsk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerListeners()


    }

    private fun init() {

        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword2 = findViewById(R.id.editTextPassword2)
        button = findViewById(R.id.button)

    }

    private fun registerListeners() {
        button.setOnClickListener {
            if (validate()) {
                val email = editTextEmailAddress.text.toString()
                val password = editTextPassword.text.toString()
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
            }
        }
    }

    private fun validate(): Boolean {
        if (editTextEmailAddress.text.toString().isEmpty()) {
            editTextEmailAddress.error = "Enter E-mail"
            return false
        } else if (editTextPassword.text.toString().isEmpty()) {
            editTextPassword.error = "Enter Password"
            return false
        } else if (editTextPassword2.text.toString().isEmpty()) {
            editTextPassword2.error = "Repeat Password"
            return false
        } else if (editTextPassword.text.toString().length < 9) {
            editTextPassword.error = "Your Password is short"
            return false
        } else if (editTextPassword.text.toString().equals(editTextPassword2.text.toString())) {
            return true
        } else if (!Passw(editTextPassword.text.toString())) {
            editTextPassword.error = "Your password must contaons both letters and numbers"
            return false
        } else if (!editTextPassword.text.toString().equals(editTextPassword2.text.toString())) {
            editTextPassword2.error = "MAke sure both passwords are matching"
            return false
        } else if (!Email(editTextEmailAddress.text.toString())) {
            editTextEmailAddress.error = "Invalid E-mail"
            return false
        } else {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
            return true
    }

         private fun Passw(password: String?): Boolean {
            password?.let {
                val pattern = "(?=/*[a-z])(?=.*[0-9])"
                val password2 = Regex(pattern)
                return password2.find(password) != null
            }
                ?: return false
        }
       private fun Email(email: String?): Boolean{
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
















