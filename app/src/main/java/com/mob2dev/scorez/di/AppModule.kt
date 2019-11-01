package com.mob2dev.scorez.di


import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.mob2dev.scorez.BuildConfig.*
import com.mob2dev.scorez.api.ApplicationService
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber.i
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class AppModule {

    @Provides
    @Singleton
    internal fun applicationService(
        okHttpClient: OkHttpClient,
        sharedPreferences: SharedPreferences
    ): ApplicationService {



        return Retrofit.Builder().baseUrl(_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(ApplicationService::class.java)
    }

    @Provides
    @Singleton
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> i(message) }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }



    // Intercept all outgoing network calls and add headers like AUTH and X-IDs
    @Provides
    @Singleton
    internal fun networkInterceptor(headers: Headers, sharedPreferences: SharedPreferences): Interceptor {
        return Interceptor {

            //val signature = sharedPreferences.getString(_SIGNATURE_KEY, APPLICATION_ID)
            //val auth = sharedPreferences.getString(_SESSION_KEY, APPLICATION_ID)


            val  newHeaders = headers.newBuilder().add("X-Device-ID","my-signature").set("Authorization","Bearer $_API_KEYS").build()

            it.proceed(it.request().newBuilder().headers(newHeaders).build())
        }
    }

   // Set default headers for all going network calls
    @Provides
    @Singleton
    internal fun headers(
        cacheControl: CacheControl
    ): Headers {

        return Headers.Builder()
            .add("Cache-control", cacheControl.toString())
            .add("Content-Type", "application/json")
            .add("Accept", "application/json;v1.1")
            .set("User-Agent", APPLICATION_ID)
            .build()
    }

    // Cache control for all request
    @Provides
    @Singleton
    internal fun cacheControl(): CacheControl {
        return CacheControl.Builder()
            .maxAge(30, SECONDS)
            .build()
    }

    //Create a cache file
    @Provides
    @Singleton
    internal fun cache(file: File): Cache {
        return Cache(file, (10 * 1000 * 1000).toLong())
    }


    //Setup okHttpClient
    @Provides
    @Singleton
    internal fun okHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkInterceptor)
            .cache(cache)
//            .connectTimeout(90, SECONDS)
//            .writeTimeout(120, SECONDS)
//            .readTimeout(120, SECONDS)
            .build()
    }


    @Provides
    @Singleton
    internal fun file(application: Application): File {
        return File(application.cacheDir, "mob-app-cache")
    }



//    @Provides
//    @Singleton
//    internal fun database(application: Application): TelaDb {
//        return Room
//                .databaseBuilder(application, TelaDb::class.java, LOCAL_DB)
//                .fallbackToDestructiveMigration()
//                .build()
//    }


    @Provides
    @Singleton
    internal fun sharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("com.mob2dev.scorez", MODE_PRIVATE)
    }

}