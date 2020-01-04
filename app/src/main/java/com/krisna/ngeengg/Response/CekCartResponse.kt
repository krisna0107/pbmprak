package com.krisna.ngeengg.Response

import com.google.gson.annotations.SerializedName

data class CekCartResponse (
    @SerializedName("cart")
    var cart: String?,
    @SerializedName("status")
    var status: Boolean?
)