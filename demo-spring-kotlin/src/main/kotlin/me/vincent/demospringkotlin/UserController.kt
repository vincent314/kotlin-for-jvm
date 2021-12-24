package me.vincent.demospringkotlin

import me.vincent.demospringkotlin.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun list(
            @RequestParam(required = false) minAmount: Double?,
    ): List<User> = userService.list(minAmount)
}
