package com.mob2dev.scorez.model

data class DeviceInfo(val name: String, val type: String)
data class AppInfo(val app_version: String)


data class UserCredentials(val email: String, val password: String)

data class NewUserInfo(
    val email:String,
    val phone:String,
    val name:String,
    val password:String,
    val password_confirmation:String
)