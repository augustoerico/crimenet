package com.couchbros.crimenet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var mCallbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()

        val facebookLogin: FacebookLoginCallback = FacebookLoginCallback()
        val loginButton = findViewById(R.id.facebook_login_button) as LoginButton

        loginButton.registerCallback(mCallbackManager, facebookLogin)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    inner class FacebookLoginCallback : FacebookCallback<LoginResult> {

        override fun onSuccess(result: LoginResult?) {
            Log.d("login", "Facebook login success")
            handleAccessToken(result?.accessToken)
        }

        override fun onCancel() {
            Log.d("login", "Facebook login canceled")
        }

        override fun onError(error: FacebookException?) {
            Log.e("login", "Error on FacebookLoginCallback", error)
            // TODO send user some feedback
        }

        private fun handleAccessToken(accessToken: AccessToken?): Unit {

            val token: String = accessToken?.token ?: ""
            Log.d("login", "Handle Facebook access accessToken [$token]")

            if (token.isNotEmpty()) {
                val credentials = FacebookAuthProvider.getCredential(token)
                mAuth.signInWithCredential(credentials)
                        .addOnCompleteListener { authResult ->
                            Log.d("login", "On complete listener")
                            if (authResult.isSuccessful) {
                                Log.d("login", "... is successful")
                                val result = authResult.result
                                val user = result.user
                            } else {
                                Log.e("login", "Error on complete listener",
                                        authResult.exception)
                                // TODO send user some feedback
                            }
                        }
            } else {
                val message = "Empty access token"
                Log.e("login", message, Exception(message))
                // TODO send user some feedback
            }

        }
    }

}
