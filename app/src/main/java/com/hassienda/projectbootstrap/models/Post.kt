package com.hassienda.projectbootstrap.models

import com.squareup.moshi.Json

class Post(
    @field:Json(name="userId")
    val userId:Int = 0,
    @field:Json(name="id")
    val id:Int = 0,
    @field:Json(name="title")
    val title:String = "",
    @field:Json(name="body")
    val body:String = ""
)