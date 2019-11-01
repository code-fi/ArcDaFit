package com.mob2dev.scorez.api

import androidx.annotation.IntDef


class Status(@param:LStatus val state: Int) {

    @Retention
    @IntDef(SUCCESS, ERROR, LOADING)
    internal annotation class LStatus

    companion object {

        const val LOADING = 0
        const val ERROR = 1
        const val SUCCESS = 2
    }
}
