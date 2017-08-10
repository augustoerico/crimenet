package com.couchbros.crimenet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser

        if (currentUser == null) {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }

        val uidTextView = findViewById(R.id.uid) as TextView

        uidTextView.text = currentUser.uid
    }

}
