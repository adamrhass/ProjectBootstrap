package com.hassienda.projectbootstrap.repository.common

sealed class Result<T>  {
    data class Success<T>(val data:T): Result<T>()
    data class Error<T>(val err:String, val code:Int?=0): Result<T>()

    fun isSuccess():Boolean{
        return this is Success
    }
    fun isError():Boolean{
        return this is Error
    }
}