package com.couchbros.crimenet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_edit_profile.setOnClickListener { onEditProfile() }
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

    private fun onEditProfile() {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

}
