package ru.partyshaker.partyshaker.data.network

interface NetworkApi {

    fun <T : Any> createApi(baseUrl: String, cls: Class<T>): T
}