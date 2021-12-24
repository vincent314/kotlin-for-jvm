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

    fun list(): List<User> {
        val columns = "ID, NAME, EMAIL, AMOUNT"

        val query = """
            SELECT $columns
             FROM USERS;
            """.trimIndent()

        return jdbcTemplate.query(query) { rs, _ ->
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
