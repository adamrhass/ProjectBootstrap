package com.hassienda.projectbootstrap.managers.interfaces

import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.repository.common.Result

interface IBootstrapManager {
    suspend fun getSlowRandomNumber(): Result<Int>
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getPost(id:Int): Result<Post>
}