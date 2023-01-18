package com.example.foodapp.data

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Warning<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Warning<*> -> "Warning[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}