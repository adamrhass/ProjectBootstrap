package com.hassienda.projectbootstrap.managers

import com.hassienda.projectbootstrap.managers.interfaces.IBootstrapManager
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.repository.common.Result
import com.hassienda.projectbootstrap.repository.interfaces.IPostsRepo
import kotlinx.coroutines.delay

class BootstrapManager(
    val postsRepo:IPostsRepo = iocGet(IPostsRepo::class.java)
):IBootstrapManager {
    override suspend fun getSlowRandomNumber(): Result<Int> {
        delay(3000)
        val num =  (Math.random() * 100).toInt()
        return if(num <= 50) Result.Success(num) else Result.Error("Cannot return number over 50")
    }

    override suspend fun getPosts(): Result<List<Post>>
    {
        return postsRepo.getPosts()
    }
    override suspend fun getPost(id:Int): Result<Post> {
        return postsRepo.getPost(id)
    }
}