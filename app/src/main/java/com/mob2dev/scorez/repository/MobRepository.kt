package com.mob2dev.scorez.repository

import android.content.SharedPreferences
import android.os.Build.MODEL
import android.os.Build.VERSION.RELEASE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mob2dev.scorez.BuildConfig.*
import com.mob2dev.scorez.api.*
import com.mob2dev.scorez.model.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MobRepository @Inject
constructor(
    private val appExecutors: AppExecutors,
    private val appService: ApplicationService,
    private val sharedPreferences: SharedPreferences
) {


}