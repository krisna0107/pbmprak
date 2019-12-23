package com.krisna.ngeengg.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krisna.ngeengg.R
import kotlinx.android.synthetic.main.activity_detail.*
import ru.slybeaver.slycalendarview.SlyCalendarDialog
import java.util.*


class DetailActivity : AppCompatActivity() {

    private lateinit var callback: SlyCalendarDialog.Callback
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mulai.setOnClickListener {
//            val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
//                jumlah.text = "$mDay/$mMonth/$mYear"
//            }, year, month, day)
//            dialog.show()
            callback = object : SlyCalendarDialog.Callback {
                override fun onCancelled() {

                }

                override fun onDataSelected(firstDate: Calendar?, secondDate: Calendar?,  hours: Int, minutes: Int) {
                    jumlah.text = firstDate.toString()
                }
            }
            SlyCalendarDialog()
                .setSingle(false)
                .setCallback(callback)
                .show(supportFragmentManager, "TAG_SLYCALENDAR")
        }
    }
}
