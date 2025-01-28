package com.conquest.acounting.management.domain.ports

import com.conquest.acounting.management.domain.model.AccountingReport
import com.conquest.acounting.management.domain.model.WsGeneralLedger

interface ReportGenerator {
    fun generateReport(template: AccountingReport, wsGeneralLedgers: List<WsGeneralLedger>): AccountingReport
}