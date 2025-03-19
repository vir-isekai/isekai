package com.vir.isekai.config

import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import mu.KotlinLogging
import org.hibernate.engine.jdbc.internal.FormatStyle

private val log = KotlinLogging.logger {}

class P6SpySqlFormatter : MessageFormattingStrategy {
	override fun formatMessage(
		connectionId: Int,
		now: String,
		elapsed: Long,
		category: String,
		prepared: String,
		sql: String,
		url: String,
	): String {
		if (sql.isBlank()) {
			return ""
		}

		val sqlUpperCase = sql.trim().uppercase()

		if (!isDMLStatement(sqlUpperCase)) {
			return sql
		}

		return buildString {
			append("\n")
			append("┌─────────────────────────────────────────────────────\n")
			append("│ SQL: ").append(formatSql(sql))
			append("\n│ Execution Time: ").append(elapsed).append(" ms")
			append("\n└─────────────────────────────────────────────────────")
		}
	}

	private fun isDMLStatement(sql: String): Boolean {
		return sql.startsWith("SELECT") ||
			sql.startsWith("INSERT") ||
			sql.startsWith("UPDATE") ||
			sql.startsWith("DELETE")
	}

	private fun formatSql(sql: String): String {
		return try {
			FormatStyle.BASIC.formatter.format(sql)
		} catch (e: Exception) {
			sql
		}
	}
}