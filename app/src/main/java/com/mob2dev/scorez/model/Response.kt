package com.mob2dev.scorez.model

import com.mob2dev.scorez.entity.User

data class MobResponse<T>(val status: Int, val message: String, val data:T?)


data class Device(val type:String,val name:String, val user_agent: String)