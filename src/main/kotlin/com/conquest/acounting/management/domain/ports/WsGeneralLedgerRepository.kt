package com.conquest.acounting.management.domain.ports

import com.conquest.acounting.management.domain.model.WsGeneralLedger

interface WsGeneralLedgerRepository {
    fun save(wsGeneralLedger: WsGeneralLedger)
    fun findAll(): List<WsGeneralLedger>
}