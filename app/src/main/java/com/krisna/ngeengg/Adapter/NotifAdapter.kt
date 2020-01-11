package com.krisna.ngeengg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krisna.ngeengg.R
import com.krisna.ngeengg.Response.Data.Pesan
import kotlinx.android.synthetic.main.item_pesan.view.*

class NotifAdapter(private val pesanData: ArrayList<Pesan>): RecyclerView.Adapter<NotifAdapter.NotifViewHolder>() {
    class NotifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(pesan: Pesan) {
            itemView.kdBook.text = pesan.kode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_pesan, parent, false)
        return NotifViewHolder(view)
    }

    override fun getItemCount(): Int = pesanData.size

    override fun onBindViewHolder(holder: NotifViewHolder, position: Int) {
        holder.bindItem(pesanData[position])
    }

}