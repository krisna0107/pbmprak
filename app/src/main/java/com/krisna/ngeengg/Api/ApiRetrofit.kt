package com.krisna.ngeengg.Api

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.Activity.DetailActivity.Companion.kembali
import com.krisna.ngeengg.Activity.DetailActivity.Companion.pinjam
import com.krisna.ngeengg.Adapter.CartAdapter
import com.krisna.ngeengg.Adapter.KontenAdapter
import com.krisna.ngeengg.Response.CekCartResponse
import com.krisna.ngeengg.Response.Data.*
import com.krisna.ngeengg.Response.KontenResponse
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRetrofit : Application(){

    private lateinit var rvKonten: RecyclerView
    private lateinit var kontenList: ArrayList<Konten>
    private lateinit var rvCart: RecyclerView
    private lateinit var cartList: ArrayList<Konten>

    companion object{
        var kdBook:String? = ""
        var extUserID:String? = ""
        var extCart:Boolean? = false
        const val EXTRA_KONTEN_ID = "extra_konten_id"
    }

    fun ApiAkun(email: String, context: Context, cls: Class<*>){
        ApiMain().services.getAkun(email).enqueue(object : Callback<Akun>{
            override fun onFailure(call: Call<Akun>, t: Throwable) {
                print("Gagal Sayangku")
            }

            override fun onResponse(call: Call<Akun>, response: Response<Akun>) {
                if (response.code() == 200 || response.code() == 201){
                    extUserID = response.body()?.id
                    val intent = Intent(context, cls)
                    startActivity(context, intent, null)
                }
            }

        })
    }

    fun ApiKonten(listKonten: RecyclerView, context: Context, cls: Class<*>){
        ApiMain().services.getKonten(0).enqueue(object : Callback<KontenResponse>{
            override fun onFailure(call: Call<KontenResponse>, t: Throwable) {
                print("Gagal Sayangku")
            }

            override fun onResponse(call: Call<KontenResponse>, response: Response<KontenResponse>) {
                kembali=""
                pinjam=""
                if(response.code() == 200){
                    if (kdBook!="")
                        print("sudah ada broo....")
                    else
                        ApiBooking(extUserID)
                    kontenList = response.body()?.data!!
                    rvKonten = listKonten
                    rvKonten.layoutManager = GridLayoutManager(context, 2)
                    val listKontenAdater = KontenAdapter(kontenList).apply {
                        setOnItemClickCallback(object : KontenAdapter.OnItemCallback{
                            override fun onItemClicked(data: Konten) {
                                val intent =  Intent(context, cls).apply {
                                    putExtra(EXTRA_KONTEN_ID, data.id)
                                }
                                startActivity(context, intent, null)
                            }
                        })
                    }
                    rvKonten.adapter = listKontenAdater
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    fun ApiKontenDetail(kotenId: String, judul: TextView, harga: TextView, btn: Button){
        ApiMain().services.getKontenDetail(kotenId).enqueue(object : Callback<Konten>{
            override fun onFailure(call: Call<Konten>, t: Throwable) {
                println("Gagal Sayangkuuuu")
            }

            override fun onResponse(call: Call<Konten>, response: Response<Konten>) {
                if (response.code() == 200 || response.code() == 201) {
                    if(extCart==false){
                        btn.text = "Tambah"
                    } else {
                        btn.text = "Hapus"
                    }
                    val getRes = response.body()!!
                    judul.text = getRes.judul
                    harga.text = getRes.harga.toString()
                }
            }

        })
    }

    fun ApiBooking(userid: String?){
        ApiMain().services.addKode(userid).enqueue(object : Callback<Pesan>{
            override fun onFailure(call: Call<Pesan>, t: Throwable) {
                println("Gagal Sayangkuuuu")
            }

            override fun onResponse(call: Call<Pesan>, response: Response<Pesan>) {
                kdBook = response.body()?.kode
            }
        })
    }

    fun ApiCart(kontenid: String?, pinjam: String, kembali: String){
        ApiMain().services.addToCart(kdBook, extUserID, kontenid, setTanggal(pinjam, kembali)).enqueue(object : Callback<Cart>{
            override fun onFailure(call: Call<Cart>, t: Throwable) {
                println("Gagal Sayangkuuuu")
            }

            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
//                val intent = Intent(context, cls)
//                startActivity(context, intent, null)
            }

        })
    }

    fun ApiCekCart(kontenid: String){
        ApiMain().services.cekCart(kdBook, extUserID, kontenid).enqueue(object : Callback<CekCartResponse>{
            override fun onFailure(call: Call<CekCartResponse>, t: Throwable) {
                println("Gagal Sayangkuuuu")
            }

            override fun onResponse(call: Call<CekCartResponse>, response: Response<CekCartResponse>) {
                if (response.code() == 200 || response.code() == 201)
                    extCart = response.body()?.status
            }
        })
    }

    fun ApiDeleteCart(kontenid: String){
        ApiMain().services.deleteCart(kdBook, extUserID, kontenid).enqueue(object : Callback<Cart>{
            override fun onFailure(call: Call<Cart>, t: Throwable) {
                println("Uhhh gagal sayang hapusnya")
            }

            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
//                val intent = Intent(context, cls)
//                startActivity(context, intent, null)
            }

        })
    }

    fun ApiGetCartUser(listCart: RecyclerView, context: Context, result: TextView){
        ApiMain().services.getCartUser(kdBook, extUserID, 0).enqueue(object : Callback<KontenResponse>{
            override fun onFailure(call: Call<KontenResponse>, t: Throwable) {
                println("Gagal Sayangkuuuu")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<KontenResponse>, response: Response<KontenResponse>) {
                cartList = response.body()!!.data
                rvCart = listCart
                rvCart.layoutManager = LinearLayoutManager(context)
                val listCart = CartAdapter(cartList)
                rvCart.adapter = listCart
                result.text = response.body()!!.total.toString()+" result"
            }
        })
    }

    fun ApiGetTotalUser(sTotal: TextView, tax: TextView, total:TextView){
        ApiMain().services.getTotalUser(kdBook, extUserID).enqueue(object : Callback<Total>{
            override fun onFailure(call: Call<Total>, t: Throwable) {

            }

            override fun onResponse(call: Call<Total>, response: Response<Total>) {
                if(response.code()==200){
                    if(response.body()!!.stotal!="0"){
                        val getRes = response.body()!!
                        sTotal.text = getRes.stotal
                        tax.text = getRes.tax
                        total.text = getRes.total
                    }
                }

            }
        })
    }
}