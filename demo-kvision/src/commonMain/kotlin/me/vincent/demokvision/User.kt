package me.vincent.demokvision

@kotlinx.serialization.Serializable
data class User (
    var fullname:String,
    var email:String,
)
