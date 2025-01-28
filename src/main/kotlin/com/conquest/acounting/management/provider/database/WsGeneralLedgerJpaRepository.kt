package com.conquest.acounting.management.provider.database

import com.conquest.acounting.management.domain.model.WsGeneralLedger
import com.conquest.acounting.management.domain.ports.WsGeneralLedgerRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class WsGeneralLedgerJpaRepository(
    private val jpaRepository: AccountingManagerRepository
) : WsGeneralLedgerRepository {

    override fun save(wsGeneralLedger: WsGeneralLedger) {
        val entity = WsGeneralLedgerEntity(
            id = UUID.randomUUID(),
            date = wsGeneralLedger.date,
            balance = wsGeneralLedger.balance,
            category = wsGeneralLedger.category,
            collectif = wsGeneralLedger.collectif,
            comment = wsGeneralLedger.comment,
            credit = wsGeneralLedger.credit,
            currency = wsGeneralLedger.currency,
            currencyAmount = wsGeneralLedger.currencyAmount,
            debit = wsGeneralLedger.debit,
            description = wsGeneralLedger.description,
            header = wsGeneralLedger.header,
            internalID = wsGeneralLedger.internalID,
            journalIndex = wsGeneralLedger.journalIndex,
            lastModifyDate = wsGeneralLedger.lastModifyDate,
            name = wsGeneralLedger.name,
            number = wsGeneralLedger.number,
            paymentMethodDescription = wsGeneralLedger.paymentMethodDescription,
            periodEnd = wsGeneralLedger.periodEnd,
            periodStart = wsGeneralLedger.periodStart,
            piece = wsGeneralLedger.piece,
            qtyUnit = wsGeneralLedger.qtyUnit,
            quantity = wsGeneralLedger.quantity,
            ref = wsGeneralLedger.ref,
            term = wsGeneralLedger.term,
            voucherID = wsGeneralLedger.voucherID,
            voucherRef = wsGeneralLedger.voucherRef
        )
        jpaRepository.save(entity)
    }

    override fun findAll(): List<WsGeneralLedger> {
        return jpaRepository.findAll().map {
            WsGeneralLedger(
                id = it.id.toString().toLong(),
                date = it.date,
                balance = it.balance,
                category = it.category,
                collectif = it.collectif,
                comment = it.comment,
                credit = it.credit,
                currency = it.currency,
                currencyAmount = it.currencyAmount,
                debit = it.debit,
                description = it.description,
                header = it.header,
                internalID = it.internalID,
                journalIndex = it.journalIndex,
                lastModifyDate = it.lastModifyDate,
                name = it.name,
                number = it.number,
                paymentMethodDescription = it.paymentMethodDescription,
                periodEnd = it.periodEnd,
                periodStart = it.periodStart,
                piece = it.piece,
                qtyUnit = it.qtyUnit,
                quantity = it.quantity,
                ref = it.ref,
                term = it.term,
                voucherID = it.voucherID,
                voucherRef = it.voucherRef
            )
        }
    }
}
