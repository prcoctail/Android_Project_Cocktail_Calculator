package ru.partyshaker.partyshaker

sealed class ApiResponse <T>(
    data: T? = null,
    exception: String? = null
) {
    data class Success <T>(val data: T) : ApiResponse<T>(data, null)

    data class Error <T>(
        val exception: String
    ) : ApiResponse<T>(null, exception)

}