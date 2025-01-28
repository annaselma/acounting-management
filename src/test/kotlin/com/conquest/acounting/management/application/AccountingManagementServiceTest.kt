package com.conquest.acounting.management.application

import com.conquest.acounting.management.domain.model.AccountingReport
import com.conquest.acounting.management.domain.model.WsGeneralLedger
import com.conquest.acounting.management.domain.ports.WsGeneralLedgerRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class AccountingManagementServiceTest {
    @Autowired
    private lateinit var accountingManagementService: AccountingManagementService

    @Mock
    private lateinit var wsGeneralLedgerRepository: WsGeneralLedgerRepository

    @Test
    fun `should generate Excel report`() {
        val transactions = listOf(
            WsGeneralLedger(balance = 100.0, category = 1, credit = 100.0, debit = 0.0, collectif = 411100, comment = null,
                currency = "EUR", currencyAmount = 0.0, date = LocalDate.now(), description = "EDF", header = "",
                internalID = "", journalIndex = 0, lastModifyDate = LocalDate.now(), name = "Test",
                number = "1", paymentMethodDescription = "", periodEnd = "", periodStart = "", piece = 0,
                qtyUnit = "", quantity = 0, ref = "", term = "", voucherID = "", voucherRef = "")
        )

        Mockito.`when`(wsGeneralLedgerRepository.findAll()).thenReturn(transactions)

        val reportTemplate = AccountingReport(name = "Test Report", balance = 0.0, profitAndLoss = 0.0, categories = mapOf("1" to 0.0))
        val result = accountingManagementService.generateReport(reportTemplate)

        assertNotNull(result)
        assertTrue(result.isNotEmpty())
    }
}
