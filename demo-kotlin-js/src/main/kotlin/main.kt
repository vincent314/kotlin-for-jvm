import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlin.js.Date

fun main() {

    document.body!!.append.div {
        h1 {
            +"Hello Kotlin-JS!"
        }
        p {
            +"Javascript generated from Kotlin"
        }
        footer {
            +"Current time:"
            span { id = "currentTime" }
        }
    }


    window.setInterval({
        document.getElementById("currentTime")!!.textContent = Date().toTimeString()
    }, 1000)

}

