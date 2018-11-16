package com.hassienda.projectbootstrap.repository.interfaces

import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.repository.common.Result

interface IPostsRepo {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getPost(id:Int): Result<Post>
}