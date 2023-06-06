package ru.partyshaker.partyshaker.data

import retrofit2.Response

fun <T> Response<T>.errorMessage(): String? = errorBody()?.string() ?: message()