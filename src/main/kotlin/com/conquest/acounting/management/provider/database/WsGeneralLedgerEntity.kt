package com.conquest.acounting.management.provider.database

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.*

@Entity
class WsGeneralLedgerEntity (
    @Id
    val id: UUID = UUID.randomUUID(),
    val balance: Double,
    val category: Int,
    val collectif: Int,
    val comment: String?,
    val credit: Double,
    val currency: String,
    val currencyAmount: Double,
    val date: LocalDate,
    val debit: Double,
    val description: String,
    val header: String,
    val internalID: String,
    val journalIndex: Long,
    val lastModifyDate: LocalDate,
    val name: String,
    val number: String,
    val paymentMethodDescription: String,
    val periodEnd: String,
    val periodStart: String,
    val piece: Int,
    val qtyUnit: String,
    val quantity: Int,
    val ref: String,
    val term: String,
    val voucherID: String,
    val voucherRef: String
)