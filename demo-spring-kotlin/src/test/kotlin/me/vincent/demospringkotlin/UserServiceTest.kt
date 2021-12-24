package me.vincent.demospringkotlin

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import me.vincent.demospringkotlin.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.ResultSet

@ExtendWith(MockKExtension::class)
internal class UserServiceTest {
    @MockK
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    @InjectMockKs
    private lateinit var userService: UserService

    @Test
    fun `should list all users with a minimum amount`() {
        val querySlot = slot<String>()
        val paramSlot = slot<Map<String, Any>>()
        val rowMapperSlot = slot<RowMapper<User>>()
        every {
            jdbcTemplate.query(capture(querySlot), capture(paramSlot), capture(rowMapperSlot))
        } returns emptyList<User>()

        userService.list(2000.0)

        querySlot.captured shouldBe """
            SELECT ID, NAME, EMAIL, AMOUNT
             FROM USERS
             WHERE AMOUNT >= :MIN_AMOUNT
            """.trimIndent()

        paramSlot.captured shouldBe mapOf("MIN_AMOUNT" to 2000.0)

        val mockResultSet = mockk<ResultSet> {
            every { getInt("ID") } returns 42
            every { getString("NAME") } returns "John Doe"
            every { getString("EMAIL") } returns "john.doe@email.com"
            every { getDouble("AMOUNT") } returns 4000.0
            every { wasNull() } returns false
        }
        rowMapperSlot.captured.mapRow(mockResultSet, 0) shouldBe User(
                id = 42,
                name = "John Doe",
                email = "john.doe@email.com",
                amount = 4000.0
        )
    }
}
