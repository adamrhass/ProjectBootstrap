package com.hassienda.projectbootstrap.managers

import com.hassienda.projectbootstrap.managers.interfaces.IBootstrapManager
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.models.Form
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.repository.common.Result
import com.hassienda.projectbootstrap.repository.interfaces.IPostsRepo
import kotlinx.coroutines.delay

class BootstrapManager(
    val postsRepo:IPostsRepo = iocGet(IPostsRepo::class.java)
):IBootstrapManager {
    //region Random Number
    override suspend fun getSlowRandomNumber(): Result<Int> {
        delay(3000)
        val num =  (Math.random() * 100).toInt()
        return if(num <= 50) Result.Success(num) else Result.Error("Cannot return number over 50")
    }
    //endregion

    //region Posts
    override suspend fun getPosts(): Result<List<Post>>
    {
        return postsRepo.getPosts()
    }
    override suspend fun getPost(id:Int): Result<Post> {
        return postsRepo.getPost(id)
    }
    //endregion

    //region Form
    override suspend fun signIn(form:Form):Result<Unit> {
        //fake the login
        delay(1000)
        return Result.Success(Unit)
    }
    //endregion
}