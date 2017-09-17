package com.goldendevloper.retrofitapp.uitls

import com.goldendevloper.retrofitapp.model.IPAddressInfo
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Abdullah Hussein on 16/09/2017.
 * abdullah.hussein109@gmail.com
 */
interface APIs {

    @GET("json")
    fun getIPAddressInfo(): Call<IPAddressInfo>

}