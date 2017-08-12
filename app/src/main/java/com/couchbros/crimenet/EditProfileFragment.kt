package com.couchbros.crimenet

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.couchbros.crimenet.enums.Platform
import com.couchbros.crimenet.enums.PlayerAlignment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class EditProfileFragment : Fragment() {

    val mAuth = FirebaseAuth.getInstance()
    val mDatabase = FirebaseDatabase.getInstance().reference

    private var container: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        this.container = container
        return inflater!!.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onStart() {
        super.onStart()

        if (mAuth.currentUser == null) {
            container?.removeView(view)
            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
        }

        edit_profile_discard.setOnClickListener { onDiscard() }
        edit_profile_save.setOnClickListener { onSave() }
    }

    private fun onSave() {

        val uid = mAuth.currentUser!!.uid
        val profile = mDatabase.child("users/$uid/profile")

        val nickname = edit_profile_nickname.text.toString()
        val alignment = PlayerAlignment.fromValues(edit_profile_law_chaos.progress,
                    edit_profile_good_evil.progress).toString()
        val platform = Platform.fromId(edit_profile_platform.checkedRadioButtonId).toString()

        if (nickname.isNullOrEmpty() || nickname.isBlank()) {
            edit_profile_nickname.error = "Field required"
            return
        }

        profile.updateChildren(mapOf(
                "alignment" to alignment,
                "platform" to platform,
                "nickname" to nickname
        ))
                .addOnCompleteListener {
                    Toast.makeText(activity, "Saved!", Toast.LENGTH_SHORT).show()
                    container?.removeView(view)
                }
                .addOnFailureListener {
                    val message = "Error while saving, check connection"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    Log.e("profile_update", message, it)
                }
    }

    private fun onDiscard() {
        container?.removeView(view)
    }
}
