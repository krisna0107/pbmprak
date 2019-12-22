package com.krisna.ngeengg.Api

import com.krisna.ngeengg.Response.Data.Akun
import com.krisna.ngeengg.Response.KontenResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("kontens/{limit}/limit")
    fun getKonten(@Path("limit") limit: Int): Call<KontenResponse>

    @GET("akuns/{email}/email")
    fun getAkun(@Path("email") email: String) : Call<Akun>
}