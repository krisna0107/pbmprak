package com.krisna.ngeengg.Api

import com.krisna.ngeengg.Response.KontenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRetrofit {

    fun ApiKonten(){
        ApiMain().services.getKonten(0).enqueue(object :
            Callback<KontenResponse>{
            override fun onFailure(call: Call<KontenResponse>, t: Throwable) {
                print("Gagal Sayangku")
            }

            override fun onResponse(call: Call<KontenResponse>, response: Response<KontenResponse>) {
                if(response.code() == 200){
                    print(response.body()!!)
                }
            }

        })
    }
}