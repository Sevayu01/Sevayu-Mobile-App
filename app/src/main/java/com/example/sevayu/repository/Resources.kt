package com.example.sevayu.repository

class Resource<T> private constructor(val status: Resource.Status, val data: T?, val apiError:Exception?) {
    enum class Status {
        SUCCESS, ERROR, LOADING , EMPTY
    }
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(apiError: Exception?): Resource<T> {
            return Resource(Status.ERROR, null, apiError)
        }
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY, null, null)
        }
    }
}