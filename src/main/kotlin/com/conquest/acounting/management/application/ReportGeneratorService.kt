package com.conquest.acounting.management.application

import com.conquest.acounting.management.domain.model.AccountingReport
import com.conquest.acounting.management.domain.model.WsGeneralLedger
import com.conquest.acounting.management.domain.ports.ReportGenerator
import com.conquest.acounting.management.domain.ports.WsGeneralLedgerRepository
import org.springframework.stereotype.Service


@Service
class ReportGeneratorService: ReportGenerator {
    override fun generateReport(template: AccountingReport, wsGeneralLedgers: List<WsGeneralLedger>): AccountingReport {
        // Step 1: Aggregate data by categories defined in the template
        val categoryTotals = template.categories.mapValues { (_, accountPrefixesString) ->
            // Ensure accountPrefixesString is a String before splitting
            val accountPrefixes = accountPrefixesString.toString().split(",").map { it.trim() }

            // Filter transactions based on account prefixes
            wsGeneralLedgers
                .filter { wsGeneralLedger ->
                    accountPrefixes.any { prefix -> wsGeneralLedger.collectif.toString().startsWith(prefix) }
                }
                .sumOf { it.balance }
        }

        // Step 2: Calculate balance and P&L
        val balance = categoryTotals.values.sum() // Total balance across all categories
        val profitAndLoss = categoryTotals
            .filterKeys { template.categories.keys.contains(it) }
            .values.sum() // Sum categories contributing to P&L

        // Step 3: Return a new AccountingReport
        return AccountingReport(
            name = template.name,
            balance = balance,
            profitAndLoss = profitAndLoss,
            categories = categoryTotals
        )
    }
}
