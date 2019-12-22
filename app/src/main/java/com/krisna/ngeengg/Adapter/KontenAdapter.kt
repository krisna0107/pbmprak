package com.krisna.ngeengg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.R
import com.krisna.ngeengg.Response.Data.Konten
import kotlinx.android.synthetic.main.item_konten.view.*

class KontenAdapter(private val kontenData: ArrayList<Konten>): RecyclerView.Adapter<KontenAdapter.KontenViewHolder>() {
    inner class KontenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(konten: Konten){
            itemView.judul.text = konten.judul
            itemView.harga.text = konten.harga.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontenViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_konten, parent, false)
        return KontenViewHolder(view)
    }

    override fun getItemCount(): Int = kontenData.size

    override fun onBindViewHolder(holder: KontenViewHolder, position: Int) {
        holder.bindItem(kontenData[position])
    }
}