package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            if (checkAllFields()) {
                loginUser(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }
        }
    }

    private fun checkAllFields(): Boolean {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Both email and password are required", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorMessage = task.exception?.message ?: "Login failed"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
