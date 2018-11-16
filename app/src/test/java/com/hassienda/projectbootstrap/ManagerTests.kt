package com.hassienda.projectbootstrap

import com.hassienda.projectbootstrap.managers.BootstrapManager
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.repository.common.Result
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import com.hassienda.projectbootstrap.repository.interfaces.IPostsRepo

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ManagerTests {

    @Test
    fun test_getSlowRandomNumber(){
        val repo = Mockito.mock(IPostsRepo::class.java)

        var num = runBlocking {
            BootstrapManager(repo).getSlowRandomNumber()
        }
        assertNotEquals( -1, num)
    }


    @Test
    fun test_getPostsSuccess(){
        //Prepare
        val repo = Mockito.mock(IPostsRepo::class.java)
        Mockito.`when`(runBlocking {  repo. getPosts() }).thenReturn( Result.Success(arrayListOf()))

        var posts = runBlocking {
            BootstrapManager(repo).getPosts()
        }
        assert(posts.isSuccess())
    }
    @Test
    fun test_getPostsFailure(){
        val repo = Mockito.mock(IPostsRepo::class.java)
        Mockito.`when`(runBlocking {  repo. getPosts() }).thenReturn( Result.Error("") )

        var posts = runBlocking {
            BootstrapManager(repo).getPosts()
        }
        assert(posts.isError())
    }

    @Test
    fun test_getPostSuccess(){
        //Prepare
        val repo = Mockito.mock(IPostsRepo::class.java)
        Mockito.`when`(runBlocking {  repo. getPost(Mockito.anyInt()) }).thenReturn( Result.Success(Post()))

        var posts = runBlocking {
            BootstrapManager(repo).getPost(1)
        }
        assert(posts.isSuccess())
    }
}
