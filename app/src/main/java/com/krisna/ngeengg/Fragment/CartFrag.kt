package com.krisna.ngeengg.Fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.krisna.ngeengg.Activity.MainActivity
import com.krisna.ngeengg.Activity.PembayaranActivity
import com.krisna.ngeengg.Activity.PembayaranActivity.Companion.EXTRA_BANK
import com.krisna.ngeengg.Activity.PembayaranActivity.Companion.EXTRA_TOTAL
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.Api.ApiRetrofit.Companion.total_harga

import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.item_cart.*
import kotlinx.android.synthetic.main.item_cart.view.*

/**
 * A simple [Fragment] subclass.
 */
class CartFrag : Fragment() {

    private lateinit var apiRetrofit: ApiRetrofit
    var getBank: String? = ""
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_cart, container, false)
        val myStrings = arrayOf("BNI", "BCA", "Permata", "Mandiri")
        rootView.spinner.adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, myStrings)
        rootView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getBank = myStrings[p2]
            }

        }
        apiRetrofit = ApiRetrofit().apply {
            context?.let { ApiGetCartUser(rootView.rvCart, it, rootView.textView, rootView.subtotal, rootView.tax, rootView.totalharga) }
            ApiGetTotalUser(rootView.subtotal, rootView.tax, rootView.totalharga)
            rootView.bayar.setOnClickListener {
//                ApiSetTotal(total_harga)
                if(total_harga!=0){
                    activity?.let { it1 ->
                        apiRetrofit.ApiaddPayment(total_harga!!.toFloat(), getBank,
                            it1
                        )
                    }

                }
            }
        }
        return rootView
    }
}
