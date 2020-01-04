package com.krisna.ngeengg.Response.Data

import com.google.gson.annotations.SerializedName

data class Pesan(
    @SerializedName("kd_book")
    var kode: String?,
    @SerializedName("user_id")
    var userid: String?,
    @SerializedName("total_harga")
    var total: Int?,
    @SerializedName("status")
    var status:String?
)