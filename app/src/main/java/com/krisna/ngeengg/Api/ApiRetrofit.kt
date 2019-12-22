package com.krisna.ngeengg.Api

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.Adapter.KontenAdapter
import com.krisna.ngeengg.Response.Data.Akun
import com.krisna.ngeengg.Response.Data.Konten
import com.krisna.ngeengg.Response.KontenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRetrofit {

    private lateinit var rvKonten: RecyclerView
    private lateinit var kontenList: ArrayList<Konten>

    fun ApiAkun(email: String, context: Context, cls: Class<*>){
        ApiMain().services.getAkun(email).enqueue(object : Callback<Akun>{
            override fun onFailure(call: Call<Akun>, t: Throwable) {
                print("Gagal Sayangku")
            }

            override fun onResponse(call: Call<Akun>, response: Response<Akun>) {
                if (response.code() == 200 || response.code() == 201){
                    val intent = Intent(context, cls)
                    startActivity(context, intent, null)
                }
            }

        })
    }

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