package me.vincent.demospringkotlin

import me.vincent.demospringkotlin.model.User
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

// Java Style
fun getDoubleOrNull(rs: ResultSet, columnName: String): Double? {
    val value = rs.getDouble(columnName)
    return if (rs.wasNull()) {
        null
    } else {
        value
    }
}

// Kotlin Style
//fun ResultSet.getDoubleOrNull(columnName: String): Double? = getDouble(columnName).takeIf { !wasNull() }

@Service
class UserService(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    @Suppress("SqlResolve", "SqlNoDataSourceInspection")
    fun list(minAmount: Double?): List<User> {
        val whereClause = minAmount?.let { "WHERE AMOUNT >= :MIN_AMOUNT" } ?: "WHERE 1=1"
        val query = """
            SELECT ID, NAME, EMAIL, AMOUNT
             FROM USERS
             $whereClause
            """.trimIndent()

        val params = minAmount?.let { mapOf("MIN_AMOUNT" to minAmount) } ?: mapOf()

        return jdbcTemplate.query(query, params) { rs, _ ->
            User(
                    id = rs.getInt("ID"),
                    name = rs.getString("NAME"),
                    email = rs.getString("EMAIL"),
                    amount = getDoubleOrNull(rs, "AMOUNT"),
//                    amount = rs.getDoubleOrNull("AMOUNT"),
            )
        }
    }
}
