package com.vir.isekai.config

import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import mu.KotlinLogging
import org.hibernate.engine.jdbc.internal.FormatStyle
import java.util.regex.Pattern

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
		log.info {
			"connectionId=$connectionId, now=$now, elapsed=$elapsed, " +
				" category=$category, prepared=$prepared, sql=$sql, url=$url"
		}

		if (sql.isNullOrBlank()) {
			return ""
		}

		val sqlUpperCase = sql.trim().uppercase()

		if (!isDMLStatement(sqlUpperCase)) {
			return sql
		}

		// 파라미터 추출 - p6spy에서 prepared가 이미 값이 바인딩된 SQL임
		val parameters =
			if (prepared != sql) {
				extractParameters(prepared, sql)
			} else {
				"None"
			}

		return buildString {
			append("\n")
			append("┌─────────────────────────────────────────────────────\n")
			append("│ SQL: ").append(formatSql(sql))
			append("\n│ Parameters: ").append(parameters)
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

	private fun extractParameters(
		preparedSql: String,
		originalSql: String,
	): String {
		try {
			// JPA나 다른 ORM 도구가 생성한 SQL은 실제 준비된 문이 포함될 수 있음
			if (preparedSql.contains("?")) {
				return "Prepared statement with placeholders"
			}

			val placeholders = ArrayList<Int>()
			var currentPos = 0

			while (true) {
				val placeholderPos = originalSql.indexOf('?', currentPos)
				if (placeholderPos == -1) break
				placeholders.add(placeholderPos)
				currentPos = placeholderPos + 1
			}

			if (placeholders.isEmpty()) {
				return "SQL without parameters"
			}

			val parameterPattern = Pattern.compile("'[^']*'|\\d+(\\.\\d+)?|NULL|true|false")
			val matcher = parameterPattern.matcher(preparedSql)

			val extractedParams = mutableListOf<String>()
			while (matcher.find() && extractedParams.size < placeholders.size) {
				extractedParams.add(matcher.group())
			}

			return if (extractedParams.isNotEmpty()) {
				extractedParams.joinToString(", ")
			} else {
				"Cannot extract parameters\nPrepared: $preparedSql\nOriginal: $originalSql"
			}
		} catch (e: Exception) {
			return "Error extracting parameters: ${e.message}"
		}
	}
}