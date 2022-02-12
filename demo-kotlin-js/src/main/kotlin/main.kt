import kotlinext.js.require
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append

@JsModule("dayjs")
external fun dayjs(): DayJs // or dynamic

external interface DayJs {
    fun format(pattern: String): String;
}

fun main() {
    require("@picocss/pico/css/pico.css")
    document.body!!.append.main("container") {
        h1 {
            +"Hello Kotlin-JS!"
        }
        p {
            +"Javascript generated from Kotlin"
        }
        p {
            val camelcaseResult = camelcase("camel-cased-string")
            +"Call Camel Case Javascript library: $camelcaseResult"
        }
        footer {
            +"Current time: "
            mark { id = "currentTime" }
        }
    }

    window.setInterval({
        document.getElementById("currentTime")!!.textContent = dayjs().format("dddd MMMM HH:mm:ss")
    }, 1000)

}

