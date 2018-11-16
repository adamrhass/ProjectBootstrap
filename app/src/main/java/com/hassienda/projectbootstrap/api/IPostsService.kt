package com.hassienda.projectbootstrap.api

import com.hassienda.projectbootstrap.models.Post
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

fun getPostService():IPostsService{
    return getRetrofit("https://jsonplaceholder.typicode.com/")
        .create(IPostsService::class.java)
}

private fun getRetrofit(base:String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(base)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(getClient())
        .build()
}


var cacheSize = 10 * 1024 * 1024 // 10MB
private fun getClient(): OkHttpClient {
    val c = OkHttpClient.Builder()
        //.cache(context.getCacheDir(), cacheSize)
        //.addInterceptor(authHeaderInterceptor)
        //.addInterceptor(unauthorizedInterceptor)
        //.readTimeout(60, TimeUnit.SECONDS)
        //.connectTimeout(60, TimeUnit.SECONDS)
        .build()
    return c
}



interface IPostsService {
    @GET("posts")
    fun getPosts():Call<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id:Int):Call<Post>
}