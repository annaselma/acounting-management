package com.conquest.acounting.management.domain.ports

import com.conquest.acounting.management.provider.parser.FileFormat

interface ImportService {
    fun parseAndStore(data: String, format: FileFormat)
    infix fun isImportServiceFor(format: FileFormat): Boolean
}