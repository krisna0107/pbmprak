package com.krisna.ngeengg.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krisna.ngeengg.R
import com.krisna.ngeengg.Adapter.NotifPagerAdapter
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
