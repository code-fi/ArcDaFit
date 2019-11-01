package com.mob2dev.scorez.api

import com.mob2dev.scorez.api.Status

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int?) {
    companion object {
        fun <T> success(data: T?, code: Int?): Resource<T> {
            return Resource(Status(Status.SUCCESS), data, null, code)
        }

        fun <T> error(msg: String, code: Int, data: T?): Resource<T> {
            return Resource(Status(Status.ERROR), data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status(Status.LOADING), data, null, null)
        }
    }
}