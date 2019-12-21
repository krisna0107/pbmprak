package com.krisna.ngeengg.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.krisna.ngeengg.fragment.DoneFrag
import com.krisna.ngeengg.fragment.PendingFrag

class NotifPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->
                DoneFrag()
            else ->
                return PendingFrag()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->
                "Selesai"
            else ->
                "Pending"
        }
    }
}