package com.hassienda.projectbootstrap

import android.app.Application
import com.hassienda.projectbootstrap.api.IPostsService
import com.hassienda.projectbootstrap.api.getPostService
import com.hassienda.projectbootstrap.ioc.IocProvider
import com.hassienda.projectbootstrap.ioc.iocSet
import com.hassienda.projectbootstrap.managers.BootstrapManager
import com.hassienda.projectbootstrap.managers.interfaces.IBootstrapManager
import com.hassienda.projectbootstrap.repository.PostsRepo
import com.hassienda.projectbootstrap.repository.interfaces.IPostsRepo

class BootstrapApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        //do bootstrapping here
        //set the api service
        iocSet(IPostsService::class.java, { getPostService() }, IocProvider.IocType.SINGLETON)
        iocSet(IPostsRepo::class.java, { PostsRepo() })
        iocSet(IBootstrapManager::class.java, { BootstrapManager() }, IocProvider.IocType.SINGLETON_LAZY)

    }
}