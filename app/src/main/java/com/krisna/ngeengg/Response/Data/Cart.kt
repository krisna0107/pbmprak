package com.krisna.ngeengg.Response.Data

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("id")
    var id: String?,
    @SerializedName("kd_book")
    var kode: String?,
    @SerializedName("konten_id")
    var kontenid: String?,
    @SerializedName("user_id")
    var userid: String?
)