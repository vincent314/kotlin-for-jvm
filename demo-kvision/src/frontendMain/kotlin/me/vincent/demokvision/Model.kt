package me.vincent.demokvision

import io.kvision.state.observableListOf

object Model {

    private val demoService = DemoService()

    val userList = observableListOf<User>()

    suspend fun ping(message: String): String {
        return demoService.ping(message)
    }

    suspend fun loadUsers() {
        val users = demoService.list()
        userList.clear()
        userList.addAll(users)
    }

    suspend fun addUser(user: User) {
        demoService.addUser(user)
        userList.add(user)
    }

}
