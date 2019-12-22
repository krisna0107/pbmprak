package com.krisna.ngeengg.Api

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.Adapter.KontenAdapter
import com.krisna.ngeengg.Response.Data.Konten
import com.krisna.ngeengg.Response.KontenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRetrofit {

    private lateinit var rvKonten: RecyclerView
    private lateinit var kontenList: ArrayList<Konten>
    fun ApiKonten(listKonten: RecyclerView, context: Context){
        ApiMain().services.getKonten(0).enqueue(object :
            Callback<KontenResponse>{
            override fun onFailure(call: Call<KontenResponse>, t: Throwable) {
                print("Gagal Sayangku")
            }

            override fun onResponse(call: Call<KontenResponse>, response: Response<KontenResponse>) {
                if(response.code() == 200){
//                    print(response.body()!!)
                    kontenList = response.body()!!.data
                    rvKonten = listKonten
                    rvKonten.layoutManager = GridLayoutManager(context, 2)
                    val listKontenAdater = KontenAdapter(kontenList)
                    rvKonten.adapter = listKontenAdater
                }
            }

        })
    }
}