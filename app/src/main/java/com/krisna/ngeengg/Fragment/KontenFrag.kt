package com.krisna.ngeengg.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krisna.ngeengg.Activity.DetailActivity
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_konten.view.*

/**
 * A simple [Fragment] subclass.
 */
class KontenFrag : Fragment() {

    private lateinit var apiRetrofit: ApiRetrofit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_konten, container, false)
        apiRetrofit = ApiRetrofit().apply {
            context?.let { ApiKonten(rootView.rvKonten, it, DetailActivity::class.java) }
        }
        return rootView
    }

    companion object {
        fun newInstance(): KontenFrag{
            val fragment = KontenFrag()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
