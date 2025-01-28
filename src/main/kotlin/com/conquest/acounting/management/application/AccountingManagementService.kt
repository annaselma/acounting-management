package com.conquest.acounting.management.application

import com.conquest.acounting.management.domain.model.AccountingReport
import com.conquest.acounting.management.provider.parser.FileFormat
import com.conquest.acounting.management.domain.ports.ImportService
import com.conquest.acounting.management.domain.ports.ReportGenerator
import com.conquest.acounting.management.domain.ports.WsGeneralLedgerRepository
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class AccountingManagementService(
    private val wsGeneralLedgerRepository: WsGeneralLedgerRepository,
    private val importServices: List<ImportService>,
    private val reportGenerator: ReportGenerator
) {
    /**
     * Import data into the system.
     * @param data Raw file content (XML/JSON/CSV).
     * @param format File format of the data.
     */
    fun pushAccountingData(data: String, format: FileFormat) {
        val parser = importServices.find { it isImportServiceFor format }
            ?: throw IllegalArgumentException("Unsupported file format: $format")

        parser.parseAndStore(data, format)
    }

    /**
     * Generate a financial report based on a given template.
     * @param template The report template (categories, aggregation rules).
     * @return ByteArray representing the report in Excel format.
     */
    fun generateReport(template: AccountingReport): ByteArray {
        val wsGeneralLedgers = wsGeneralLedgerRepository.findAll()
        val report = reportGenerator.generateReport(template.copy(), wsGeneralLedgers)
        return convertReportToExcel(report)
    }

    private fun convertReportToExcel(report: AccountingReport): ByteArray {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Financial Report")

        // Add header row
        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("Category")
        headerRow.createCell(1).setCellValue("Value")

        // Populate categories into rows
        var rowIndex = 1
        for ((category, value) in report.categories) {
            val row = sheet.createRow(rowIndex++)
            row.createCell(0).setCellValue(category)
            row.createCell(1).setCellValue(value)
        }

        // Add summary data
        val summaryRow = sheet.createRow(rowIndex++)
        summaryRow.createCell(0).setCellValue("Balance")
        summaryRow.createCell(1).setCellValue(report.balance)

        val profitLossRow = sheet.createRow(rowIndex)
        profitLossRow.createCell(0).setCellValue("Profit and Loss")
        profitLossRow.createCell(1).setCellValue(report.profitAndLoss)

        // Convert the workbook into a byte array
        ByteArrayOutputStream().use { outputStream ->
            workbook.write(outputStream)
            return outputStream.toByteArray()
        }
    }
}