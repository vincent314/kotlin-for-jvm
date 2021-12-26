import kotlinext.js.require
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlin.js.Date

fun main() {
    require("@picocss/pico/css/pico.css")
    document.body!!.append.main("container") {
        h1 {
            +"Hello Kotlin-JS!"
        }
        p {
            +"Javascript generated from Kotlin"
        }
        footer {
            +"Current time: "
            mark { id = "currentTime" }
        }
    }

    window.setInterval({
        document.getElementById("currentTime")!!.textContent = Date().toTimeString()
    }, 1000)

}

