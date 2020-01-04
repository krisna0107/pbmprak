package com.krisna.ngeengg.Response.Data

import com.google.gson.annotations.SerializedName

class Total (
    @SerializedName("subtotal")
    var stotal: String,
    @SerializedName("tax")
    var tax: String,
    @SerializedName("total")
    var total: String
)