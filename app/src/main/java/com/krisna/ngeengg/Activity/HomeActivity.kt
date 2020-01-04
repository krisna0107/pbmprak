package com.krisna.ngeengg.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.Fragment.CartFrag
import com.krisna.ngeengg.Fragment.KontenFrag
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_cart.*


class HomeActivity : AppCompatActivity() {

    private lateinit var apiRetrofit: ApiRetrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(bar)
        val fragment = KontenFrag.newInstance()
        addFragment(fragment)
        fab.setOnClickListener {
            bar.hideOnScroll=false
            addFragment(CartFrag())
        }
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
            R.id.notif -> println("NOTIF gaes")
        }
        return true
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, frag, frag.javaClass.simpleName)
            .commit()
    }
}
