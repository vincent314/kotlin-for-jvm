package me.vincent.demospringkotlin.model

data class User(
        val id: Int,
        val name: String,
        val email: String,
        val amount: Double?,
)
