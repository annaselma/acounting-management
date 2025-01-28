package com.conquest.acounting.management.domain.model

data class WCFRestIRFGeneralLegerResult(
    val data: GeneralLedgerData,
    val response: ResponseData
)

data class GeneralLedgerData(
    val wsGeneralLedger: List<WsGeneralLedger>
)

data class ResponseData(
    val result: String,
    val message: String,
    val dateTime: String,
    val duration : String
)