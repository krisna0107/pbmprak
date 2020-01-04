package com.krisna.ngeengg.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.Api.ApiRetrofit
import com.krisna.ngeengg.R
import com.krisna.ngeengg.Response.Data.Konten
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(private val cartData: ArrayList<Konten>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private lateinit var apiRetrofit: ApiRetrofit
    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItem(cart: Konten){
            itemView.judul.text = cart.judul
            itemView.harga.text = "Rp. "+cart.harga.toString()
            itemView.hapus.setOnClickListener {
                apiRetrofit = ApiRetrofit().apply {
                    ApiDeleteCart(cart.id!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = cartData.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindItem(cartData[position])
    }
}