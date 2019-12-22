package com.krisna.ngeengg.Response.Data

import com.google.gson.annotations.SerializedName

data class Akun (
    @SerializedName("id")
    var id: String?,
    @SerializedName("email")
    var email: String?
)