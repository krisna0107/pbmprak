package com.krisna.ngeengg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.R
import com.krisna.ngeengg.Response.Data.Konten
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_konten.view.*
import java.util.*
import kotlin.collections.ArrayList

class KontenAdapter(private val kontenData: ArrayList<Konten>): RecyclerView.Adapter<KontenAdapter.KontenViewHolder>() {
    private lateinit var onItemCallback: OnItemCallback

    interface OnItemCallback {
        fun onItemClicked(data: Konten)
    }

    fun setOnItemClickCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    inner class KontenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(konten: Konten){
            itemView.judul.text = konten.judul
            itemView.harga.text = konten.harga.toString()
            Picasso.get().load("http://ykyj.xyz/"+konten.photo).into(itemView.photos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontenViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_konten, parent, false)
        return KontenViewHolder(view)
    }

    override fun getItemCount(): Int = kontenData.size

    override fun onBindViewHolder(holder: KontenViewHolder, position: Int) {
        holder.bindItem(kontenData[position])
        holder.itemView.setOnClickListener { onItemCallback.onItemClicked(kontenData[holder.adapterPosition]) }
    }
}