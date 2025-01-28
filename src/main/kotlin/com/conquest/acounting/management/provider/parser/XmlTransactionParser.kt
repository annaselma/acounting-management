package com.conquest.acounting.management.provider.parser

import com.conquest.acounting.management.domain.ports.ImportService
import com.conquest.acounting.management.domain.ports.WsGeneralLedgerRepository
import com.conquest.acounting.management.domain.model.WCFRestIRFGeneralLegerResult
import com.conquest.acounting.management.provider.parser.mapper.WsGeneralLedgerMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.stereotype.Component

@Component
class XmlTransactionParser( private val wsGeneralLedgerRepository: WsGeneralLedgerRepository) : ImportService {
    override fun parseAndStore(data: String, format: FileFormat) {
        if (format != FileFormat.XML) throw IllegalArgumentException("Unsupported format")

        val xmlMapper = XmlMapper().apply {
            registerKotlinModule()  // Enables Kotlin support in Jackson
        }

        val root: WCFRestIRFGeneralLegerResult = xmlMapper.readValue(data)

        val wsGeneralLedgers = WsGeneralLedgerMapper.mapToWsGeneralLedgers(root.data)


        wsGeneralLedgers.forEach { wsGeneralLedgerRepository.save(it) }
    }


    override fun isImportServiceFor(format: FileFormat): Boolean {
        return format == FileFormat.XML
    }
}
