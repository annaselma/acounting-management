package com.conquest.acounting.management.domain.model

data class AccountingReport (
    val  name: String,
    val balance: Double,
    val profitAndLoss: Double,
    val categories: Map<String, Double>
)