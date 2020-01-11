package com.krisna.ngeengg.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krisna.ngeengg.Adapter.NotifPagerAdapter
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.fragment_notif.view.*

/**
 * A simple [Fragment] subclass.
 */
class NotifFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rvNotif = inflater.inflate(R.layout.fragment_notif, container, false)
        val fragmentAdapterView = NotifPagerAdapter(childFragmentManager)
        rvNotif.pager_main.adapter = fragmentAdapterView
        rvNotif.tabs.setupWithViewPager(rvNotif.pager_main)

        return rvNotif
    }


}
