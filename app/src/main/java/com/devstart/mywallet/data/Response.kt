package com.devstart.mywallet.data

sealed class Response

data class Success<T>(val data: T): Response()
class Failure(val throwable: Throwable): Response()