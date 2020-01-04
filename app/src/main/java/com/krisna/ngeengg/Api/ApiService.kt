package com.krisna.ngeengg.Api

import com.krisna.ngeengg.Response.CekCartResponse
import com.krisna.ngeengg.Response.Data.*
import com.krisna.ngeengg.Response.KontenResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("kontens/{limit}/limit")
    fun getKonten(@Path("limit") limit: Int): Call<KontenResponse>

    @GET("kontens/{id}/konten")
    fun getKontenDetail(@Path("id") id: String) : Call<Konten>

    @GET("akuns/{email}/email")
    fun getAkun(@Path("email") email: String) : Call<Akun>

    @POST("pesans/{userid}/user")
    fun addKode(@Path("userid") userid: String?) : Call<Pesan>

    @GET("carts/{kdbook}/booking/{userid}/user/{limit}/limit")
    fun getCartUser(@Path("kdbook") kdbook: String?,
                    @Path("userid") userid: String?,
                    @Path("limit") limit: Int) : Call<KontenResponse>

    @GET("carts/{kdbook}/booking/{userid}/user/{kontenid}/konten")
    fun cekCart(@Path("kdbook") kdbook: String?,
                @Path("userid") userid: String?,
                @Path("kontenid") kontenid: String?) : Call<CekCartResponse>

    @GET("carts/{kdbook}/booking/{userid}/user/total")
    fun getTotalUser(@Path("kdbook") kdbook: String?,
                     @Path("userid") userid: String?) : Call<Total>

    @POST("carts/{kdbook}/booking/{userid}/user/{kontenid}/konten")
    fun addToCart(@Path("kdbook") kdbook: String?,
                  @Path("userid") userid: String?,
                  @Path("kontenid") kontenid: String?,
                  @Body setTanggal: setTanggal) : Call<Cart>

    @DELETE("carts/{kdbook}/booking/{userid}/user/{kontenid}/konten")
    fun deleteCart(@Path("kdbook") kdbook: String?,
                   @Path("userid") userid: String?,
                   @Path("kontenid") kontenid: String?): Call<Cart>
}