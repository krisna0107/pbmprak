package com.krisna.ngeengg.Response

import com.krisna.ngeengg.Response.Data.PGW

class PGWResponse (
    var order_id: String?,
    var transaction_status: String?,
    var gross_amount: Float?,
    var va_numbers: ArrayList<PGW>
)