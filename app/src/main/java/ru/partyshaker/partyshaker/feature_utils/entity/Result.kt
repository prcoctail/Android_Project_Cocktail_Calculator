package ru.partyshaker.partyshaker.feature_utils.entity

sealed class Result <T>(
    data: T? = null,
    exception: String? = null
) {
    data class Success <T>(val data: T) : Result<T>(data, null)

    data class Error <T>(
        val exception: String
    ) : Result<T>(null, exception)

}