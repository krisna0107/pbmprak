package com.krisna.ngeengg.Api

import com.krisna.ngeengg.Response.*
import com.krisna.ngeengg.Response.Data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("kontens/{limit}/limit")
    fun getKonten(@Path("limit") limit: Int): Call<KontenResponse>

    @GET("kontens/{id}/konten")
    fun getKontenDetail(@Path("id") id: String) : Call<Konten>

    @POST("akuns/{email}/email")
    fun getAkun(@Path("email") email: String) : Call<Akun>

    @GET("pesans/{userid}/user/{stat}/status/{limit}/limit")
    fun getPesanByStat(@Path("userid") userid: String?,
                       @Path("stat") stat: String?,
                       @Path("limit") limit: Int) : Call<PesanResponse>

    @POST("pesans/{kdbook}/booking/{userid}/user/{total}/set/{bank}/bank/{va}/va/{status}")
    fun setTotal(@Path("kdbook") kdbook: String?,
                 @Path("userid") userid: String?,
                 @Path ("total") total: Int?,
                 @Path("bank") bank: String?,
                 @Path("va") va: String?,
                 @Path("status") status: String?) : Call<Pesan>

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

    @GET("carts/{kontenid}/konten/{pinjam}/pinjam/{kembali}/kembali")
    fun cekStock(@Path("kontenid") kontenid: String?,
                @Path("pinjam") pinjam: String?,
                @Path("kembali") kembali: String?) : Call<CekCartResponse>

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

    //Payment GateWay
//    @POST("mids/{amount}/harga/{kdbook}/book/{userid}/user/{bank}/bank")
//    fun addPay(@Body pgwReq: PGWReq) : Call<PGWResponse>

    @POST("mids/{amount}/harga/{kdbook}/book/{userid}/user/{bank}/bank")
    fun addPay(@Path("amount") amount: Float?,
               @Path("kdbook") kdbook: String?,
               @Path("userid") userid: String?,
               @Path("bank") bank: String?) : Call<PGWResponse>

    @GET("mids/{kdbook}/book/status")
    fun getStatus(@Path("kdbook") kdbook: String?) : Call<PGWResponse>


}