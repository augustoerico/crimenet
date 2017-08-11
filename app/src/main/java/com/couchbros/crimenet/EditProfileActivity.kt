package com.couchbros.crimenet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.couchbros.crimenet.enums.Platform
import com.couchbros.crimenet.enums.PlayerAlignment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.content_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    val mDatabase = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            return
        }

        edit_profile_discard.setOnClickListener { onDiscard(currentUser.uid) }
        edit_profile_save.setOnClickListener { onSave(currentUser.uid) }
    }

    private fun onSave(uid: String) {

        val intent = Intent(this, MainActivity::class.java)

        val profile = mDatabase.child("users/$uid/profile")
        val alignment = PlayerAlignment.fromValues(edit_profile_law_chaos.progress,
                edit_profile_good_evil.progress)

        profile.updateChildren(mapOf(
                "alignment" to alignment.toString(),
                "platform" to Platform.PS4.toString(),
                "nickname" to "KikoDSV"
        ))
                .addOnCompleteListener {
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                .addOnFailureListener {
                    val message = "Error while saving, check connection"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    Log.e("profile_update", message, it)
                    startActivity(intent)
                }

    }

    private fun onDiscard(uid: String) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
