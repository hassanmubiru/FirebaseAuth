package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    private val splashDelay: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }, splashDelay)
    }
}
