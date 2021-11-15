package com.example.mytaskactivity

import java.util.*
import java.io.Serializable

data class UserDetails(
    var id : String = UUID.randomUUID().toString(),
    var firstname : String = "",
    var secondname : String = "",
    var age : String = ""
) : Serializable