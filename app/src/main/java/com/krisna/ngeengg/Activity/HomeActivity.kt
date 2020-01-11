package com.krisna.ngeengg.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.BaseCallback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.result.UserProfile
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.Fragment.CartFrag
import com.krisna.ngeengg.Fragment.KontenFrag
import com.krisna.ngeengg.Fragment.NotifFrag
import com.krisna.ngeengg.R
import com.krisna.ngeengg.setLogin
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    val fragment = KontenFrag.newInstance()
    private lateinit var apiRetrofit: ApiRetrofit
    private var usersClient: UsersAPIClient? = null
    private var authenticationAPIClient: AuthenticationAPIClient? = null
    private var auth0: Auth0? = null
    private lateinit var sharedPreference: setLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(bar)
        auth0 = Auth0(this).apply {
            isOIDCConformant = true
        }

        sharedPreference = setLogin(this)
        val accessToken = sharedPreference.getVal("TOKEN")
        usersClient = UsersAPIClient(auth0, accessToken)
        authenticationAPIClient = AuthenticationAPIClient(auth0!!)
        getProfile(accessToken!!)

        ApiRetrofit().ApiAkun(sharedPreference.getVal("EMAIL")!!)
        fab.setOnClickListener {
            bar.hideOnScroll=false
            addFragment(CartFrag())
        }

        println("token = $accessToken")
        println("Clear = "+sharedPreference.getVal("CLEAR"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.home -> {
                bar.hideOnScroll=true
                addFragment(KontenFrag.newInstance())
            }
            R.id.notif -> {
                bar.hideOnScroll=true
                addFragment(NotifFrag())
            }
            R.id.logout-> {
                logout()
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        addFragment(fragment)
    }

    @SuppressLint("PrivateResource")
    fun addFragment(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, frag, frag.javaClass.simpleName)
            .commit()
    }

    private fun logout() {
        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra(MainActivity.EXTRA_CLEAR_CREDENTIALS, true)
        sharedPreference.save("CLEAR", "logout")
        startActivity(intent)
        finish()
    }

    private fun getProfile(accessToken: String) {
        authenticationAPIClient!!.userInfo(accessToken)
            .start(object : BaseCallback<UserProfile, AuthenticationException?> {
                override fun onSuccess(userinfo: UserProfile) {
                    usersClient!!.getProfile(userinfo.id)
                        .start(object : BaseCallback<UserProfile?, ManagementException?> {
                            override fun onSuccess(profile: UserProfile?) { // Display the user profile
                                apiRetrofit = ApiRetrofit().apply {
                                    if (profile != null) {
                                        sharedPreference.save("EMAIL", profile.email)
                                        sharedPreference.save("NAMA", profile.name)
                                        ApiRetrofit().ApiAkun(sharedPreference.getVal("EMAIL")!!)

                                        println(" halo nama ku " + profile.name)
                                    }
                                }
                                println("emaiilll $profile.email")
                                addFragment(fragment)
                            }

                            override fun onFailure(error: ManagementException?) {
                                println("gagal sayang JSON")
                                println(error!!.localizedMessage)
                            }
                        })
                }

                override fun onFailure(error: AuthenticationException?) {
                    println("gagal sayang Auth")
                    println(error!!.localizedMessage)
                }
            })
    }
}
