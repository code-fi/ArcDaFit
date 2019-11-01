package com.mob2dev.scorez.api

import retrofit2.Response

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error", 0)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val code = response.code()
                if (body == null || code == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body,
//                            linkHeader = response.headers()?.get("link"),
                        code = code
                    )
                }
            } else {
                val msg = response.errorBody()?.string()

                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg, code = response.code())
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T, val code: Int) : ApiResponse<T>()

data class ApiErrorResponse<T>(var errorMessage: String, val code: Int) : ApiResponse<T>() {
    init {
        when (code) {
            0 -> errorMessage = "Device internet connection down; 000"
            404 -> errorMessage = "URL not found; 404"
            500 -> errorMessage = "Server application error; 500"
        }
    }
}