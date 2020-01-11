package com.krisna.ngeengg.Activity

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.Api.ApiRetrofit.Companion.noVA
import com.krisna.ngeengg.Fragment.CartFrag
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_pembayaran.*

class PembayaranActivity : AppCompatActivity() {

    private lateinit var apiRetrofit: ApiRetrofit
    companion object{
        const val EXTRA_TOTAL = "extra_total"
        const val EXTRA_BANK = "extra_bank"
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        val getTotal:Int = intent.getIntExtra(EXTRA_TOTAL, 0)
        val getBank: String? = intent.getStringExtra(EXTRA_BANK)
        apiRetrofit = ApiRetrofit()
        payVA.text = "Virtual Account $getBank"
        norek.text = noVA
        stotal.text = "Rp. $getTotal"
        konfirmasi.setOnClickListener {
            apiRetrofit.ApiGetStatus(getTotal, getBank)
            finish()
        }
    }
}
