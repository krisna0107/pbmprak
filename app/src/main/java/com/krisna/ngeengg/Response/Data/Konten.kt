package com.krisna.ngeengg.Response.Data

import com.google.gson.annotations.SerializedName

data class Konten (
    @SerializedName("id")
    var id: String?,
    @SerializedName("judul")
    var judul: String?,
    @SerializedName("harga")
    var harga: Int?,
    @SerializedName("url_photo")
    var photo: String
)