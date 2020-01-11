package com.krisna.ngeengg.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.Api.ApiRetrofit.Companion.EXTRA_KONTEN_ID
import com.krisna.ngeengg.Api.ApiRetrofit.Companion.extCart
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_detail.*
import ru.slybeaver.slycalendarview.SlyCalendarDialog
import java.text.SimpleDateFormat
import java.util.*


class DetailActivity : AppCompatActivity() {

    private lateinit var callback: SlyCalendarDialog.Callback
    private lateinit var apiRetrofit: ApiRetrofit
    @SuppressLint("SetTextI18n")

    companion object{
        var pinjam: String = ""
        var kembali: String = ""
    }
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val konten = intent.getStringExtra(EXTRA_KONTEN_ID)
        apiRetrofit = ApiRetrofit().apply {
            ApiCekCart(konten)
            ApiKontenDetail(konten, judul, harga, tambahhapus, img_d)
            tambahhapus.setOnClickListener {
                if (pinjam!="" && kembali!=""){
                    if (!extCart!!) {
//                        ApiCart(konten, pinjam, kembali)
                        ApiCekStock(konten, pinjam, kembali, this@DetailActivity, this@DetailActivity)
                    }else {
                        ApiDeleteCart(konten)
                        finish()
                        Toast.makeText(this@DetailActivity, "Hapus", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if(extCart!!) {
                        ApiDeleteCart(konten)
                        finish()
                        Toast.makeText(this@DetailActivity, "Hapus", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(this@DetailActivity, "Belum set Tanggal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            extCart=false
        }
        mulai.setOnClickListener {
            callback = object : SlyCalendarDialog.Callback {
                override fun onCancelled() {

                }

                override fun onDataSelected(firstDate: Calendar?, secondDate: Calendar?,  hours: Int, minutes: Int) {
                    if (firstDate != null) {
                        pinjam = SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(firstDate.time)
                        //Start
                        sBulan.text = SimpleDateFormat(getString(R.string.bulan), Locale.getDefault()).format(firstDate.time)
                        sTgl.text = SimpleDateFormat(getString(R.string.tgl), Locale.getDefault()).format(firstDate.time)
                        sTahun.text = SimpleDateFormat(getString(R.string.tahun), Locale.getDefault()).format(firstDate.time)
                    }
                    //End
                    if (secondDate != null) {
                        kembali = SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.time)
                        eBulan.text = SimpleDateFormat(getString(R.string.bulan), Locale.getDefault()).format(secondDate.time)
                        eTgl.text = SimpleDateFormat(getString(R.string.tgl), Locale.getDefault()).format(secondDate.time)
                        eTahun.text = SimpleDateFormat(getString(R.string.tahun), Locale.getDefault()).format(secondDate.time)
                    }
                }
            }
            SlyCalendarDialog().setSingle(false).setCallback(callback).show(supportFragmentManager, "TAG_KRISNAYANAJAVISTA")
        }
    }
}
