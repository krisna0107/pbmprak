package com.krisna.ngeengg.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_detail.*
import ru.slybeaver.slycalendarview.SlyCalendarDialog
import java.text.SimpleDateFormat
import java.util.*


class DetailActivity : AppCompatActivity() {

    private lateinit var callback: SlyCalendarDialog.Callback
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        mulai.setOnClickListener {
            callback = object : SlyCalendarDialog.Callback {
                override fun onCancelled() {

                }

                override fun onDataSelected(firstDate: Calendar?, secondDate: Calendar?,  hours: Int, minutes: Int) {
                    if (firstDate != null) {
                        jumlah.text = SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(firstDate.time)
                    }
                }
            }
            SlyCalendarDialog().setSingle(false).setCallback(callback).show(supportFragmentManager, "TAG_KRISNAYANAJAVISTA")
        }
    }
}
