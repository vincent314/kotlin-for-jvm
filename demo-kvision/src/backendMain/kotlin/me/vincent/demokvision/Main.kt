package me.vincent.demokvision

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.Compression
import io.ktor.routing.routing
import io.kvision.remote.applyRoutes
import io.kvision.remote.kvisionInit

fun Application.main() {
    install(Compression)
    routing {
        applyRoutes(DemoServiceManager)
    }
    kvisionInit()
}
