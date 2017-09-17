package com.goldendevloper.retrofitapp.uitls

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Abdullah Hussein on 16/09/2017.
 * abdullah.hussein109@gmail.com
 */
class AppRetrofit {

    companion object {

        //The Base URL you can change it to your Base URL
        private val BASE_URL = "http://ip-api.com/";
        private val TIME_OUT: Long = 30;

        private lateinit var mInterceptor: Interceptor
        private lateinit var mOkHttpClient: OkHttpClient
        private lateinit var mRetrofit: Retrofit

        lateinit var mAPIs: APIs

        /**
         * initialize the retrofit library for the app
         */
        fun initialize() {
            try {
                mInterceptor = Interceptor { chain ->
                    chain.proceed(chain
                            .request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("Accept-Language", "EN")
                            .addHeader("Authorization", "")
                            .addHeader("Accept-Charset", "utf-8")
                            .build())
                }

                mOkHttpClient = OkHttpClient.Builder()
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .addInterceptor(mInterceptor)
                        .build()

                mRetrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(mOkHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(Gson()))
                        .build()

                mAPIs = mRetrofit.create(APIs::class.java)

            } catch (e: Exception) {
                throw Exception("AppRetrofit not initialized")
            }
        }

        /**
         * return the client object that used to call http request.
         */
        fun client(): APIs {
            try {
                return mAPIs
            } catch (e: Exception) {
                throw Exception("AppRetrofit not initialized")
            }
        }

        /**
         * cancel all requests in retrofit enqueue.
         */
        fun cancelAllRequests() {
            try {
                mOkHttpClient.dispatcher().cancelAll()
            } catch (e: Exception) {
                throw Exception("AppRetrofit not initialized")
            }
        }
    }
}
