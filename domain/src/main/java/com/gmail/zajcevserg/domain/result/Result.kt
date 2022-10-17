package com.gmail.zajcevserg.domain.result

sealed class Result {
    class Success<T> : Result() {
        var successBody: T? = null
    }

    class Error(
        val message: String
    ) : Result()

}