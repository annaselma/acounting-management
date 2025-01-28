package com.conquest.acounting.management.api

import com.conquest.acounting.management.application.AccountingManagementService
import com.conquest.acounting.management.provider.parser.FileFormat
import com.conquest.acounting.management.domain.model.AccountingReport
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accounting")
class AccountingResource(private val accountingManagementService: AccountingManagementService) {

    @PostMapping("/_push-data")
    @ResponseStatus(HttpStatus.CREATED)
    fun pushAccountingData(
        @RequestParam format: FileFormat,
        @RequestBody data: String
    ): ResponseEntity<String> {
        accountingManagementService.pushAccountingData(data, format)
        return ResponseEntity.ok("Data imported successfully")
    }

    @GetMapping("/_generate", produces = ["application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"])
    fun generateReport(@RequestBody template: AccountingReport): ResponseEntity<ByteArray> {
        val excelFile = accountingManagementService.generateReport(template)
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx")
            .body(excelFile)
    }
}