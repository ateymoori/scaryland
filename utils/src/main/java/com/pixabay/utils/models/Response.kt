package com.pixabay.utils.models

sealed class Response<out T>

data class Success<T>(val data: T?) : Response<T>()
data class Loading(val msg: String?) : Response<Any?>()
data class ErrorIn(val code: Int?=null, val message: String?) : Response<Any?>()