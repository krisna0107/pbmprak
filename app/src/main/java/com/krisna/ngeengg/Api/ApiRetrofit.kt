package com.krisna.ngeengg.Api

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.ContactsContract
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.krisna.ngeengg.Activity.DetailActivity
import com.krisna.ngeengg.Activity.DetailActivity.Companion.kembali
import com.krisna.ngeengg.Activity.DetailActivity.Companion.pinjam
import com.krisna.ngeengg.Activity.MainActivity
import com.krisna.ngeengg.Activity.PembayaranActivity
import com.krisna.ngeengg.Adapter.CartAdapter
import com.krisna.ngeengg.Adapter.KontenAdapter
import com.krisna.ngeengg.Adapter.NotifAdapter
import com.krisna.ngeengg.Response.*
import com.krisna.ngeengg.Response.Data.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.temporal.TemporalAmount

class ApiRetrofit : Application(){

    private lateinit var rvKonten: RecyclerView
    private lateinit var kontenList: ArrayList<Konten>
    private lateinit var rvCart: RecyclerView
    private lateinit var cartList: ArrayList<Konten>
    private lateinit var rvPesan: RecyclerView
    private lateinit var pesanList: ArrayList<Pesan>

    companion object{
        var kdBook:String? = ""
        var noVA: String? = ""
        var extUserID:String? = ""
        var extCart:Boolean? = false
        var total_harga: Int? = 0
        var trStatus: String?=""
        const val EXTRA_KONTEN_ID = "extra_konten_id"
    }

    fun ApiAkun(email: String){
        ApiMain().services.getAkun(email).enqueue(object : Callback<Akun>{
            override fun onFailure(call: Call<Akun>, t: Throwable) {
                print("Gagal Sayangku")
                println("Akun "+t.localizedMessage)
            }

            override fun onResponse(call: Call<Akun>, response: Response<Akun>) {
                if (response.code() == 200 || response.code() == 201){
                    extUserID = response.body()?.id
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
                if (kdBook!="")
                    print("sudah ada broo....")
                else
                    ApiBooking(extUserID)
                if(response.code() == 200){
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
    fun ApiKontenDetail(kotenId: String, judul: TextView, harga: TextView, btn: Button, img: ImageView){
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
                    Picasso.get().load("http://ykyj.xyz/"+getRes.photo).into(img)
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

    fun ApiCekStock(kontenid: String?, pinjam: String, kembali: String, ctx: Context, activity: DetailActivity){
        ApiMain().services.cekStock(kontenid, pinjam, kembali).enqueue(object : Callback<CekCartResponse>{
            override fun onFailure(call: Call<CekCartResponse>, t: Throwable) {

            }

            @SuppressLint("ShowToast")
            override fun onResponse(call: Call<CekCartResponse>, response: Response<CekCartResponse>) {
                if (response.body()!!.cart=="tersedia"){
                    ApiCart(kontenid, pinjam, kembali)
                    activity.finish()
                }else{
                    Toast.makeText(ctx, "Mobil tidak tersedia ditanggal tersebut", Toast.LENGTH_SHORT).show()
                }
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

    fun ApiGetCartUser(listCart: RecyclerView, context: Context, result: TextView, sTotal: TextView, tax: TextView, total:TextView){
        ApiMain().services.getCartUser(kdBook, extUserID, 0).enqueue(object : Callback<KontenResponse>{
            override fun onFailure(call: Call<KontenResponse>, t: Throwable) {
                println("Gagal Sayangkuuuu")
                println("Cart User "+t.localizedMessage)
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<KontenResponse>, response: Response<KontenResponse>) {
                cartList = response.body()!!.data
                rvCart = listCart
                rvCart.layoutManager = LinearLayoutManager(context)
                val listCartAdapter = CartAdapter(cartList).apply {
                    setOnItemClickCallback(object : CartAdapter.OnItemCallback{
                        override fun onItemClicked(data: Konten) {
                            ApiDeleteCart(data.id!!)
                            ApiGetCartUser(listCart, context, result, sTotal, tax, total)
                            ApiGetTotalUser(sTotal, tax, total)
                            ApiGetCartUser(listCart, context, result, sTotal, tax, total)
                        }
                    })
                }
                rvCart.adapter = listCartAdapter
                result.text = response.body()!!.total.toString()+" result"
            }
        })
    }

    fun ApiGetTotalUser(sTotal: TextView, tax: TextView, total:TextView){
        ApiMain().services.getTotalUser(kdBook, extUserID).enqueue(object : Callback<Total>{
            override fun onFailure(call: Call<Total>, t: Throwable) {
                println("Total User "+t.localizedMessage)
            }

            override fun onResponse(call: Call<Total>, response: Response<Total>) {
                if(response.code()==200){
                    val getRes = response.body()!!
                    if(getRes.stotal!="0"){
                        sTotal.text = getRes.stotal
                        tax.text = getRes.tax
                        total.text = getRes.total
                        total_harga = getRes.total.toInt()
                    }else{
                        if(getRes.tax.toInt()==2000) {
                            tax.text = "0"
                            total.text = "0"
                        }else{
                            tax.text = getRes.tax
                            total.text = getRes.total
                        }
                        sTotal.text = getRes.stotal
                        total_harga = getRes.total.toInt()
                    }
                }
            }
        })
    }

    fun ApiSetTotal(total: Int?, bank: String?, va: String?, status: String?){
        ApiMain().services.setTotal(kdBook, extUserID, total, bank, va, status).enqueue(object : Callback<Pesan>{
            override fun onFailure(call: Call<Pesan>, t: Throwable) {

            }

            override fun onResponse(call: Call<Pesan>, response: Response<Pesan>) {
                if(response.code()==200){
                    kdBook = ""
                    total_harga = 0
                    ApiBooking(extUserID)
                }
            }
        })
    }

    fun ApiGetPesanByStat(stat: String?, listPesan: RecyclerView, context: Context){
        ApiMain().services.getPesanByStat(extUserID, stat, 0).enqueue(object : Callback<PesanResponse>{
            override fun onFailure(call: Call<PesanResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<PesanResponse>, response: Response<PesanResponse>) {
                pesanList = response.body()!!.data
                rvPesan = listPesan
                rvPesan.layoutManager = LinearLayoutManager(context)
                val listPesanAdater = NotifAdapter(pesanList)
                rvPesan.adapter = listPesanAdater
            }
        })
    }
    fun ApiaddPayment(amount: Float?, bank: String?, fragmentActivity: FragmentActivity){
        ApiMain().services.addPay(amount, kdBook, extUserID, bank).enqueue(object : Callback<PGWResponse>{
            override fun onFailure(call: Call<PGWResponse>, t: Throwable) {
                println("VA gagal")
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<PGWResponse>, response: Response<PGWResponse>) {
                if (response.code()==200){
                    noVA = response.body()!!.va_numbers[0].va_number
                    val intent = Intent(fragmentActivity, PembayaranActivity::class.java).apply {
                        putExtra(PembayaranActivity.EXTRA_TOTAL, total_harga!!)
                        putExtra(PembayaranActivity.EXTRA_BANK, bank)
                    }
                    fragmentActivity.startActivity(intent)
                }
            }

        })
    }

    fun ApiGetStatus(total: Int?, bank: String?){
        ApiMain().services.getStatus(kdBook).enqueue(object : Callback<PGWResponse>{
            override fun onFailure(call: Call<PGWResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<PGWResponse>, response: Response<PGWResponse>) {
                trStatus = response.body()!!.transaction_status
                ApiSetTotal(total, bank, noVA, trStatus)
            }

        })
    }
}