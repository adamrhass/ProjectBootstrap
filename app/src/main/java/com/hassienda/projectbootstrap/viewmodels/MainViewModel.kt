package com.hassienda.projectbootstrap.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.managers.interfaces.IBootstrapManager
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.viewmodels.common.AsyncAction

class MainViewModel(
    private val bootManager:IBootstrapManager = iocGet(IBootstrapManager::class.java)

):ViewModel() {
    //The view will call this method when it wants a new number
    val requestNumberAction = AsyncAction{
        bootManager.getSlowRandomNumber()
    }
    class PostItemModel(val post:Post)
    {
        fun select(){}
    }

    // get all posts
    val posts = AsyncAction{
        bootManager.getPosts()
    }.also { it.run() }



    val selectedPostId = MutableLiveData<Int>()
    fun selectPostById(id:Int?){
        selectedPostId.value = id
        if(id != null){
            selectedPost.run()
        }
    }

    // get a post
    val selectedPost = AsyncAction{
        selectedPostId.value?.let {
            bootManager.getPost(it)
        }
    }
}