package me.vincent.demokvision

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
actual class DemoService : IDemoService {

    override suspend fun ping(message: String): String {
        println(message)
        return "Hello world from server!"
    }

    override suspend fun list(): List<User> {
        return File("../users.json")
                .inputStream()
                .use { inputStream ->
                    Json.decodeFromStream(inputStream)
                }
    }

    override suspend fun addUser(user: User) {
        val users = list().toMutableList()
        users.add(user)
        File("../users.json")
                .outputStream()
                .use { outputStream ->
                    Json.encodeToStream(users, outputStream)
                }
    }
}
