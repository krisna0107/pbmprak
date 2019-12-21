package com.krisna.ngeengg.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.krisna.ngeengg.R
import com.krisna.ngeengg.adapter.NotifPagerAdapter
import kotlinx.android.synthetic.main.activity_notif.*

class NotifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif)

        val fragmentAdapterView = NotifPagerAdapter(supportFragmentManager)
        pager_main.adapter = fragmentAdapterView
        tabs.setupWithViewPager(pager_main)
    }
}
