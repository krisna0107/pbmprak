package com.krisna.ngeengg.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.R

class MainActivity : AppCompatActivity() {

    private lateinit var apiRetrofit: ApiRetrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiRetrofit = ApiRetrofit().apply {
            ApiKonten()
        }
    }
}
