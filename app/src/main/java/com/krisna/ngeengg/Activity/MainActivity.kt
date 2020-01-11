package com.krisna.ngeengg.Activity

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.Auth0
import com.auth0.android.Auth0Exception
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.AuthCallback
import com.auth0.android.provider.VoidCallback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.provider.WebAuthProvider.logout
import com.auth0.android.result.Credentials
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.R
import com.krisna.ngeengg.setLogin
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var auth0: Auth0? = null
    private lateinit var sharedPreference: setLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference = setLogin(this)
        auth0 = Auth0(this).apply {
            isOIDCConformant = true
        }

        if (sharedPreference.getVal("CLEAR").equals("login")) {
            showNextActivity()
        }else{
            login.setOnClickListener {
                login()
            }
        }

        println("Clear = "+sharedPreference.getVal("CLEAR"))
    }

    private fun login() {
        WebAuthProvider.login(auth0!!)
            .withScheme("demo")
            .withScope("openid profile email offline_access read:current_user update:current_user_metadata")
            .withAudience(
                String.format(
                    "https://%s/api/v2/",
                    getString(R.string.com_auth0_domain)
                )
            )//"https://%s/userinfo",
            .start(this@MainActivity, object : AuthCallback {
                override fun onSuccess(credentials: Credentials) {
                    sharedPreference.save("TOKEN", credentials.accessToken)
                    println("Token new = ${credentials.accessToken}")
                    println("fresh tkn = ${credentials.refreshToken}")
                    println("Date = ${credentials.expiresAt}")
                    println("Long = ${credentials.expiresIn}")
                    println("Scope = ${credentials.scope}")
                    println("Type = ${credentials.type}")
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                    sharedPreference.save("CLEAR", "login")
                    finish()
                }

                override fun onFailure(dialog: Dialog) {
                    println("gagal sayang JSON")
                }

                override fun onFailure(exception: AuthenticationException?) {
                    println("gagal sayang Auth")
                }

            })
    }

    private fun logout() {
        auth0?.let {
            logout(it)
                .withScheme("demo")
                .start(this, object : VoidCallback {
                    override fun onSuccess(payload: Void?) {

                    }

                    override fun onFailure(error: Auth0Exception?) {
                        showNextActivity()
                    }

                })
        }
    }

    private fun showNextActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
