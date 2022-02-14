package me.vincent.demokvision

import io.kvision.*
import io.kvision.core.FontWeight
import io.kvision.data.dataContainer
import io.kvision.form.formPanel
import io.kvision.form.text.Text
import io.kvision.html.Div
import io.kvision.html.button
import io.kvision.html.label
import io.kvision.panel.GridPanel
import io.kvision.panel.VPanel
import io.kvision.panel.root
import io.kvision.utils.perc
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class UserListComponent : VPanel() {
    init {
        dataContainer(Model.userList, { (fullname, email), _, _ ->
            label(fullname) {
                fontWeight = FontWeight.BOLDER
            }
            label(email)
        }, container = GridPanel {
            gridTemplateColumns = "50% 50%"
            padding = 24.px
        })
    }
}

class UserFormComponent : Div() {
    init {
        maxWidth = 300.px
        formPanel<UserForm> {
            add(UserForm::fullname, Text(label = "User's full name"))
            add(UserForm::email, Text(label = "User's email"))
            button("Create") {
                onClick {
                    val newUser = User(
                            this@formPanel[UserForm::fullname] as String,
                            this@formPanel[UserForm::email] as String)
                    AppScope.launch {
                        Model.addUser(newUser)
                    }
                }
            }
        }
    }
}

class App : Application() {

    override fun start(state: Map<String, Any>) {
        val root = root("kvapp") {
            padding = 10.perc
        }
        AppScope.launch {
            root.add(UserListComponent())
            root.add(UserFormComponent())
            Model.loadUsers()
        }
    }
}

fun main() {
    startApplication(
            ::App,
            module.hot,
            BootstrapModule,
            BootstrapCssModule,
            CoreModule
    )
}
