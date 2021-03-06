package com.krisna.ngeengg.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krisna.ngeengg.Api.ApiRetrofit

import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.fragment_done.view.*

/**
 * A simple [Fragment] subclass.
 */
class DoneFrag : Fragment() {

    private lateinit var apiRetrofit: ApiRetrofit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootViewNotif = inflater.inflate(R.layout.fragment_done, container, false)
        apiRetrofit = ApiRetrofit().apply {
            context?.let { ApiGetPesanByStat("D", rootViewNotif.rvPesan, it) }
        }
        return rootViewNotif
    }


}
