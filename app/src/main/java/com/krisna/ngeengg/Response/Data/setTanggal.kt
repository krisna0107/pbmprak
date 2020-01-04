package com.krisna.ngeengg.Response.Data

import com.google.gson.annotations.SerializedName

data class setTanggal (
    @SerializedName("pinjam")
    var tglPinjam: String?,
    @SerializedName("kembali")
    var tglKembali: String?
)