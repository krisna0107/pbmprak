package com.krisna.ngeengg.Response

import com.krisna.ngeengg.Response.Data.Bank
import com.krisna.ngeengg.Response.Data.TrfDetail

class PGWReq (
    var payment_type: String = "bank_transfer",
    var transaction_details: TrfDetail?,
    var bank_transfer: Bank?
)