package com.mob2dev.scorez.api

import androidx.lifecycle.LiveData
import com.mob2dev.scorez.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApplicationService {

    @POST("assert-device")
    fun assertDevice(@Body device: DeviceInfo): LiveData<ApiResponse<MobResponse<String>>>

}
