package com.krisna.ngeengg.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krisna.ngeengg.Api.ApiRetrofit

import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

/**
 * A simple [Fragment] subclass.
 */
class CartFrag : Fragment() {

    private lateinit var apiRetrofit: ApiRetrofit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_cart, container, false)
        apiRetrofit = ApiRetrofit().apply {
            context?.let { ApiGetCartUser(rootView.rvCart, it, rootView.textView) }
            ApiGetTotalUser(rootView.subtotal, rootView.tax, rootView.totalharga)
        }
        return rootView
    }
}
