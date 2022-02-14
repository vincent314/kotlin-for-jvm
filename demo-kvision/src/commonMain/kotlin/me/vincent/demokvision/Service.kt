package me.vincent.demokvision

import io.kvision.annotations.KVService

@KVService
interface IDemoService {
    suspend fun ping(message: String): String
    suspend fun list(): List<User>
    suspend fun addUser(user: User)
}
