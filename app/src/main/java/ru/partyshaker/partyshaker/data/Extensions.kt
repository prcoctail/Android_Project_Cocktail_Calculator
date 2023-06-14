package ru.partyshaker.partyshaker.data

import org.json.JSONObject
import retrofit2.Response

private const val DETAIL = "detail"
private const val ERROR = "error"

fun <T> Response<T>.errorMessage(): String? = errorBody()?.string()?.let { it ->
    try {
        JSONObject(it).let {
            it.getString(
                DETAIL
            ).ifEmpty { it.getString(ERROR) }
        }
    } catch (e: java.lang.Exception) {
        ""
    }
} ?: message()

fun <T> Response<T>.success() = isSuccessful && body() != null && errorBody() == null

fun <T> Response<T>.error() = ErrorT(errorMessage(), code())

fun <T> Response<T>.result() =
    if (success()) ResultT.Success(body()!!) else ResultT.Failure(
        error()
    )

data class ErrorT(val message: String?, val code: Int = -1)

val emptyError: ErrorT by lazy { ErrorT("") }

sealed class ResultT<T>(
    val isSuccess: Boolean,
    val value: T?,
    val error: ErrorT? = null
) {

    class Success<T>(valueT: T?) : ResultT<T>(true, valueT)
    class Failure<T>(error: ErrorT) : ResultT<T>(false, value = null, error = error)
}
