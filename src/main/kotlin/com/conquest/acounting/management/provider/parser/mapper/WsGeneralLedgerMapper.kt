package com.conquest.acounting.management.provider.parser.mapper

import com.conquest.acounting.management.domain.model.WsGeneralLedger
import com.conquest.acounting.management.domain.model.GeneralLedgerData

object WsGeneralLedgerMapper {
    fun mapToWsGeneralLedgers(data: GeneralLedgerData): List<WsGeneralLedger> {
        return data.wsGeneralLedger.map {
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
