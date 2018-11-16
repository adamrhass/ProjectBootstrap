package com.hassienda.projectbootstrap.repository

import com.hassienda.projectbootstrap.api.IPostsService
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.repository.common.Result
import com.hassienda.projectbootstrap.repository.interfaces.IPostsRepo
import com.hassienda.projectbootstrap.repository.common.await

class PostsRepo:IPostsRepo {
    val service = iocGet(IPostsService::class.java)

    override suspend fun getPosts(): Result<List<Post>> {
        return service.getPosts().await()
    }
    override suspend fun getPost(id:Int): Result<Post> {
        val r = service.getPost(id).await()
        return r
    }
}