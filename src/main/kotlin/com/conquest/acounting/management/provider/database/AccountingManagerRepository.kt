package com.conquest.acounting.management.provider.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountingManagerRepository : JpaRepository<WsGeneralLedgerEntity, UUID> {
}